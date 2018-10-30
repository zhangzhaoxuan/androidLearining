package org.view.adapter;

import cn.bmob.v3.BmobObject;

public class clientIfo extends BmobObject {
    private String usersName;
    private String code;
   public void setUsersName(String usersNamename)
    {
        this.usersName=usersNamename;
    }
   public String getUsersName()
    {
        return usersName;
    }
   public void setCode(String code)
    {
        this.code=code;
    }
   public String getCode(String code)
    {
       return code;
    }
}
