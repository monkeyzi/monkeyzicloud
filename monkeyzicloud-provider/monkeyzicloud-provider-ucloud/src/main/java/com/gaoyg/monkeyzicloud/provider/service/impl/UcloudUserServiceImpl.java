package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.enums.LogTypeEnum;
import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.commom.core.util.RequestUtils;
import com.gaoyg.monkeyzicloud.constant.GlobalConstant;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.enums.UcloudUserSourceEnum;
import com.gaoyg.monkeyzicloud.provider.enums.UcloudUserTypeEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudUserMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.*;
import com.gaoyg.monkeyzicloud.provider.model.dto.UserRole.UserBindRoleVo;
import com.gaoyg.monkeyzicloud.provider.model.dto.role.BindRoleDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.user.BindUserRolesDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.user.UserModifyPwdDto;
import com.gaoyg.monkeyzicloud.provider.service.*;
import com.gaoyg.monkeyzicloud.provider.utils.Md5Util;
import com.gaoyg.monkeyzicloud.util.pubutils.PublicUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.netflix.discovery.converters.Auto;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author: 高yg
 * @date: 2018/8/5 21:02
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UcloudUserServiceImpl extends BaseService<UcloudUser> implements UcloudUserService {

    @Resource
    private UcloudUserMapper ucloudUserMapper;
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private UcloudTokenService ucloudTokenService;

    @Autowired
    private UcloudLogService ucloudLogService;

    @Autowired
    private UcloudGroupUserService ucloudGroupUserService;

    @Autowired
    private UcloudRoleUserService ucloudRoleUserService;

    @Autowired
    private UcloudRoleService ucloudRoleService;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UcloudUser findByLoginName(String loginName) {
        log.info("用户登录名为:{}",loginName);
        return ucloudUserMapper.findByLoginName(loginName);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UcloudUser findUserInfoByUserId(Long userId) {
        return ucloudUserMapper.selectUserInfoByUserId(userId);
    }

    @Override
    public int updateUserInfo(UcloudUser ucloudUser) {
        log.info("更新用户的信息,uclouduser={}",ucloudUser);
        int updateResult=ucloudUserMapper.updateByPrimaryKeySelective(ucloudUser);
        if (updateResult<1){
            log.info("用户【 {} 】修改用户信息失败", ucloudUser.getId());
        }else {
            log.info("用户【 {} 】修改用户信息成功",ucloudUser.getId());
        }
        return updateResult;
    }

    /**
     * 处理用户的登录信息
     * @param token
     * @param principal
     * @param request
     */
    @Override
    public void handlerLoginData(OAuth2AccessToken token, User principal, HttpServletRequest request) {
        //获取浏览器信息
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-Agent"));
        //获取客户端操作系统
        final String os = userAgent.getOperatingSystem().getName();
        //获取客户端浏览器
        final String browser = userAgent.getBrowser().getName();
        //获取Ip
        final String remoteAddr = RequestUtils.getRemoteAddr(request);
        //获取访问路径
        final String requestURI = request.getRequestURI();
        //根据ip获取位置信息 TODO
        final String remoteLocation = "";
        UcloudUser ucloudUser=new UcloudUser();
        UcloudUser user=ucloudUserMapper.findByLoginName(principal.getUsername());
        ucloudUser.setId(user.getId());
        ucloudUser.setLastLoginIp(remoteAddr);
        ucloudUser.setLastLoginLocation(remoteLocation);
        ucloudUser.setLastLoginTime(new Date());
        LoginAuthDto loginAuthDto=new LoginAuthDto(user.getId(),user.getLoginName(),user.getUserName());
        //token信息
        String access_token=token.getValue();
        String refresh_token=token.getRefreshToken().getValue();
        String tokenType=token.getTokenType();
        //记录用户token信息
        log.info("保存用户的token信息");
        ucloudTokenService.saveUserToken(access_token,refresh_token,tokenType,loginAuthDto,request);
        //更新用户的最后登录时间
        taskExecutor.execute(()->this.updateUserInfo(ucloudUser));
        //记录用户登录日志
        UcloudLog ulog=new UcloudLog();
        ulog.setGroupId(user.getGroupId());
        ulog.setGroupName(user.getGroupName());
        ulog.setBrowser(browser);
        ulog.setOs(os);
        ulog.setIp(remoteAddr);
        ulog.setLocation(remoteLocation);
        ulog.setRequestUrl(requestURI);
        ulog.setLogType(LogTypeEnum.LOGIN_LOG.getType());
        ulog.setLogName(LogTypeEnum.LOGIN_LOG.getName());
        //执行保存日志
        taskExecutor.execute(()->ucloudLogService.saveLog(ulog,loginAuthDto));
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public PageInfo queryUserListWithPage(UcloudUser user) {
        PageHelper.offsetPage(user.getPageNum(),user.getPageSize());
        PageHelper.orderBy("u.update_time desc");
        List<UcloudUser> ucloudUserList=ucloudUserMapper.selectUserList(user);
        return new PageInfo(ucloudUserList);
    }

    @Override
    public void saveUcloudUser(UcloudUser user, LoginAuthDto loginAuthDto) {
        String loginName=user.getLoginName();
        Preconditions.checkArgument(!StringUtils.isEmpty(loginName),ErrorCodeEnum.UCLOUD10012004.getMsg());
        Preconditions.checkArgument(user.getGroupId() != null, ErrorCodeEnum.UCLOUD10013001.getMsg());
        user.setUpdateInfo(loginAuthDto);
        //新用户
        if (user.isNew()){
            String loginPwd=user.getLoginPwd();
            Preconditions.checkArgument(!StringUtils.isEmpty(loginPwd),ErrorCodeEnum.UCLOUD10012005.getMsg());
            user.setLoginPwd(Md5Util.encrypt(loginPwd));
            //验证用户名是否存在
            UcloudUser query = new UcloudUser();
            query.setLoginName(loginName);
            int count = ucloudUserMapper.selectCount(query);
            if (count > 0) {
                throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011025, loginName);
            }
            //保存用户
            user.setType(UcloudUserTypeEnum.OPERATE.getKey());
            user.setUserSource(UcloudUserSourceEnum.INSERT.getKey());
            ucloudUserMapper.insertSelective(user);
            //添加组织关联
            UcloudGroupUser groupUser=new UcloudGroupUser();
            groupUser.setGroupId(user.getGroupId());
            //TODO
            groupUser.setUserId(user.getId());

            //保存用户组织关系
            ucloudGroupUserService.updateByUserId(groupUser);
        }else {
            UcloudUser ucloudUser=ucloudUserMapper.selectByPrimaryKey(user.getId());
            Preconditions.checkArgument(ucloudUser != null, "用户不存在");
             // 1.更新用户信息
            int updateInt = ucloudUserMapper.updateUcloudUser(user);
            if (updateInt < 1) {
                throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011026, user.getId());
            }
            // 2.绑定组织信息
            UcloudGroupUser uacGroupUser = ucloudGroupUserService.queryByUserId(user.getId());
            if (uacGroupUser == null) {
                // 添加组织关联
                UcloudGroupUser groupUser = new UcloudGroupUser();
                groupUser.setGroupId(user.getGroupId());
                groupUser.setUserId(user.getId());
                ucloudGroupUserService.save(groupUser);
            } else {
                //修改组织
                UcloudGroupUser groupUser = new UcloudGroupUser();
                groupUser.setUserId(user.getId());
                groupUser.setGroupId(user.getGroupId());
                ucloudGroupUserService.updateByUserId(groupUser);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public List<UcloudLog> queryUserLogListWithUserId(Long userId) {
        if (PublicUtil.isEmpty(userId)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011001);
        }
        return ucloudLogService.queryUserLogListByUserId(userId);
    }

    @Override
    public int modifyUserStatusById(UcloudUser user, LoginAuthDto loginAuthDto) {
        Long loginUserId=loginAuthDto.getUserId();
        Long userId=user.getId();
        if (loginUserId.equals(userId)){
            throw  new UcloudBizException(ErrorCodeEnum.UCLOUD10011023);
        }
        UcloudUser ucloudUser=ucloudUserMapper.selectByPrimaryKey(userId);
        if (ucloudUser==null){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011011,userId);
        }
        user.setVersion(ucloudUser.getVersion()+1);
        user.setUpdateInfo(loginAuthDto);
        return ucloudUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUserById(Long userId) {
        return ucloudUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public UserBindRoleVo getUserBindRoleDto(Long userId) {
        UserBindRoleVo userBindRoleVo=new UserBindRoleVo();
        Set<Long> alreadyBindRole=Sets.newHashSet();
        UcloudUser user=this.queryUserById(userId);
        if (user==null){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011011,userId);
        }
        //查询所有可以被绑定的角色，包含该用户已经绑定的角色
        List<BindRoleDto> bindRoleDtoList=ucloudUserMapper.selectAllNeedBindRole(GlobalConstant.Sys.SUER_MANAGE_ROLE_ID);
        //查询用户已经绑定的角色
        List<UcloudRoleUser> alreadyBindList=ucloudRoleUserService.listByUserId(userId);
        Set<BindRoleDto> allBindSet=Sets.newHashSet(bindRoleDtoList);
        alreadyBindList.stream().forEach(a->{
            alreadyBindRole.add(a.getRoleId());
        });
        userBindRoleVo.setAllRoleSet(allBindSet);
        userBindRoleVo.setAlreadyBindRoleIdSet(alreadyBindRole);
        return userBindRoleVo;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public UcloudUser queryUserById(Long userId) {
        UcloudUser user= ucloudUserMapper.selectByPrimaryKey(userId);
        if (PublicUtil.isNotEmpty(user)) {
            user.setLoginPwd("");
        }
        return user;
    }

    @Override
    public void bindUserRoles(BindUserRolesDto bindUserRolesDto, LoginAuthDto loginAuthDto) {
        if (bindUserRolesDto == null) {
            log.error("参数不能为空");
            throw new IllegalArgumentException("参数不能为空");
        }
        Long operUserId = bindUserRolesDto.getUserId();
        Long loginUserId = loginAuthDto.getUserId();
        if (Objects.equals(operUserId,loginUserId)){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011023);
        }
        //前台传过来的角色Id列表
        List<Long> roleIdList = bindUserRolesDto.getRoleIdList();
        if (null == operUserId) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011001);
        }
        if (Objects.equals(operUserId,GlobalConstant.Sys.SUER_MANAGE_ROLE_ID)){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012003);
        }
        UcloudUser ucloudUser=this.queryUserById(operUserId);
        if (ucloudUser==null){
            log.info("找不到用户信息");
            throw  new UcloudBizException(ErrorCodeEnum.UCLOUD10011011,operUserId);
        }
        //超级管理员不允许操作
        if (PublicUtil.isNotEmpty(roleIdList)&&roleIdList.contains(GlobalConstant.Sys.SUER_MANAGE_ROLE_ID)){
            throw  new UcloudBizException(ErrorCodeEnum.UCLOUD10011023);
        }

       //查询该用户已经绑定的角色信息
        List<UcloudRoleUser> ucloudRoleUserList=ucloudRoleUserService.listByUserId(operUserId);
        //删除已经绑定的信息
        if (PublicUtil.isNotEmpty(ucloudRoleUserList)){
            ucloudRoleUserService.deleteByUserId(operUserId);
        }
        //更新用户的操作时间
        final  UcloudUser user=new UcloudUser();
        user.setUpdateInfo(loginAuthDto);
        user.setId(operUserId);
        ucloudUserMapper.updateUcloudUser(user);
        if (PublicUtil.isEmpty(roleIdList)){
            log.info("用户角色解除绑定成功");
            return;
        }
       roleIdList.stream().forEach(a->{
           UcloudRole ucloudRole=ucloudRoleService.getRoleById(a);
           if (ucloudRole==null){
               log.info("找不到对应的角色信息");
               throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012008,a);
           }
           ucloudRoleUserService.saveRoleUser(operUserId,a);
       });
    }

    @Override
    public void resetLoginPwd(Long userId, LoginAuthDto loginAuthDto) {

    }

    @Override
    public int userModifyPwd(UserModifyPwdDto userModifyPwdDto, LoginAuthDto authResDto) {
        String loginName = userModifyPwdDto.getLoginName();
        String oldPassword = userModifyPwdDto.getOldPassword();
        String newPassword = userModifyPwdDto.getNewPassword();
        String confirmPwd = userModifyPwdDto.getConfirmPwd();

        Preconditions.checkArgument(!PublicUtil.isEmpty(loginName), ErrorCodeEnum.UCLOUD10011007.getMsg());
        Preconditions.checkArgument(!PublicUtil.isEmpty(oldPassword), ErrorCodeEnum.UCLOUD10011006.getMsg());
        Preconditions.checkArgument(!PublicUtil.isEmpty(newPassword), ErrorCodeEnum.UCLOUD10011008.getMsg());
        Preconditions.checkArgument(!PublicUtil.isEmpty(confirmPwd), ErrorCodeEnum.UCLOUD10011009.getMsg());
        Preconditions.checkArgument(newPassword.equals(confirmPwd), "两次密码不一致, 请重新输入！");

        UcloudUser user=ucloudUserMapper.findByLoginName(loginName);
        if (user==null){
            throw  new UcloudBizException(ErrorCodeEnum.UCLOUD10011011,loginName);
        }
        String oldPwd=user.getLoginPwd();
        String newEncrptPwd=Md5Util.encrypt(newPassword);
        if (!Md5Util.matches(oldPassword, oldPwd)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011035);
        }
        //新密码和原始密码不能相同
        if (Md5Util.matches(newPassword, oldPwd)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011036);
        }
        UcloudUser ucloudUser=new UcloudUser();
        ucloudUser.setLoginPwd(newEncrptPwd);
        ucloudUser.setId(user.getId());
        ucloudUser.setIsChangedPwd(Short.valueOf("1"));
        ucloudUser.setUpdateInfo(authResDto);
        return ucloudUserMapper.updateByPrimaryKeySelective(ucloudUser);
    }
}
