package com.gaoyg.monkeyzicloud.provider.model.dto.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gaoyg.monkeyzicloud.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/13 21:41
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "日志dto")
public class QueryLogDto extends BaseQuery {
    private static final long serialVersionUID = 3967075132487249652L;
    /**
     * 日志类型
     */
    @ApiModelProperty(value = "日志类型")
    private String logType;
    /**
     * 操作用户的名称
     */
    @ApiModelProperty(value = "操作用户的名称")
    private String creater;
    /**
     * 权限名
     */
    @ApiModelProperty(value = "权限名称")
    private String actionName;
    /**
     * 权限编码
     */
    @ApiModelProperty(value = "权限编码")
    private String actionCode;
    /**
     * 菜单+Id集合
     */
    @ApiModelProperty(value = "菜单id集合")
    private List<Long> menuIdList;
    /**
     * 菜单Id
     */
    @ApiModelProperty(value = "菜单Id")
    private Long menuId;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始查询时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-Mm-dd Hh:mm:ss",timezone = "GWT+8")
    private Date queryBeginTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束查询时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd Hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GWT+8")
    private Date queryEndTime;
    /**
     * 登录Ip
     */
    @ApiModelProperty(value = "登录IP")
    private String ip;
    /**
     * 登录位置
     */
    @ApiModelProperty(value = "登录位置")
    private String location;



}
