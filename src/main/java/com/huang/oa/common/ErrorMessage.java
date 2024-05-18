package com.huang.oa.common;

import lombok.Data;


public class ErrorMessage {
    public static final String USER_NAME_ALREADY_EXISTS = "用户名已存在";
    public static final String USER_NOT_REGISTERED = "用户未注册";
    public static final String WRONG_PASSWORD = "密码错误";
    public static final String APPLICATION_ALREADY_EXISTS = "申请已存在";
    public static final String LACK_OF_AUTHORITY = "用户权限不足";
    public static final String NOT_MODIFIED = "请假原因未修改";
    public static final String NOT_SAME_DEPARTMENT = "员工不是同一部门";
    public static final String WORKING_GROUP_ALREADY_EXISTS = "工作组已存在";
    public static final String GROUP_FAILURE = "分组失败";
    public static final String FAILED_TO_ADD_STAFF = "工作组成员添加失败";
    public static final String WORK_ALREADY_EXISTS = "工作内容已存在";
    public static final String ALREADY_OVER = "工作已经结束，无法更改进度";
    public static final String FAILED_MAKE_AN_ANNOUNCEMENT = "发布公告失败";
}
