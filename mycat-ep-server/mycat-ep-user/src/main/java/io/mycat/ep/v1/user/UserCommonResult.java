// **********************************************************************
//
// Copyright (c) 2003-2015 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.0
//
// <auto-generated>
//
// Generated from file `User.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package io.mycat.ep.v1.user;

public class UserCommonResult implements java.lang.Cloneable, java.io.Serializable
{
    public int status;

    public UserCommonResult()
    {
    }

    public UserCommonResult(int status)
    {
        this.status = status;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        UserCommonResult _r = null;
        if(rhs instanceof UserCommonResult)
        {
            _r = (UserCommonResult)rhs;
        }

        if(_r != null)
        {
            if(status != _r.status)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::user::UserCommonResult");
        __h = IceInternal.HashUtil.hashAdd(__h, status);
        return __h;
    }

    public UserCommonResult
    clone()
    {
        UserCommonResult c = null;
        try
        {
            c = (UserCommonResult)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeInt(status);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        status = __is.readInt();
    }

    static public void
    __write(IceInternal.BasicStream __os, UserCommonResult __v)
    {
        if(__v == null)
        {
            __nullMarshalValue.__write(__os);
        }
        else
        {
            __v.__write(__os);
        }
    }

    static public UserCommonResult
    __read(IceInternal.BasicStream __is, UserCommonResult __v)
    {
        if(__v == null)
        {
             __v = new UserCommonResult();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UserCommonResult __nullMarshalValue = new UserCommonResult();

    public static final long serialVersionUID = 5569590422108121820L;
}
