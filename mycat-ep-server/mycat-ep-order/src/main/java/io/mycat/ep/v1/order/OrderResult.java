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
// Generated from file `order.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package io.mycat.ep.v1.order;

public class OrderResult implements java.lang.Cloneable, java.io.Serializable
{
    public int status;

    public long orderId;

    public String orderCode;

    public OrderResult()
    {
        orderCode = "";
    }

    public OrderResult(int status, long orderId, String orderCode)
    {
        this.status = status;
        this.orderId = orderId;
        this.orderCode = orderCode;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        OrderResult _r = null;
        if(rhs instanceof OrderResult)
        {
            _r = (OrderResult)rhs;
        }

        if(_r != null)
        {
            if(status != _r.status)
            {
                return false;
            }
            if(orderId != _r.orderId)
            {
                return false;
            }
            if(orderCode != _r.orderCode)
            {
                if(orderCode == null || _r.orderCode == null || !orderCode.equals(_r.orderCode))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::order::OrderResult");
        __h = IceInternal.HashUtil.hashAdd(__h, status);
        __h = IceInternal.HashUtil.hashAdd(__h, orderId);
        __h = IceInternal.HashUtil.hashAdd(__h, orderCode);
        return __h;
    }

    public OrderResult
    clone()
    {
        OrderResult c = null;
        try
        {
            c = (OrderResult)super.clone();
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
        __os.writeLong(orderId);
        __os.writeString(orderCode);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        status = __is.readInt();
        orderId = __is.readLong();
        orderCode = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, OrderResult __v)
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

    static public OrderResult
    __read(IceInternal.BasicStream __is, OrderResult __v)
    {
        if(__v == null)
        {
             __v = new OrderResult();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final OrderResult __nullMarshalValue = new OrderResult();

    public static final long serialVersionUID = -5306075526648152943L;
}
