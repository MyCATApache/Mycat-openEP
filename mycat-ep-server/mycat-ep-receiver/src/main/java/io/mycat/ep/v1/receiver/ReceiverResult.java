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
// Generated from file `receiver.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package io.mycat.ep.v1.receiver;

public class ReceiverResult implements java.lang.Cloneable, java.io.Serializable
{
    public int status;

    public PurchaseReceiver[] receivers;

    public ReceiverResult()
    {
    }

    public ReceiverResult(int status, PurchaseReceiver[] receivers)
    {
        this.status = status;
        this.receivers = receivers;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        ReceiverResult _r = null;
        if(rhs instanceof ReceiverResult)
        {
            _r = (ReceiverResult)rhs;
        }

        if(_r != null)
        {
            if(status != _r.status)
            {
                return false;
            }
            if(!java.util.Arrays.equals(receivers, _r.receivers))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::receiver::ReceiverResult");
        __h = IceInternal.HashUtil.hashAdd(__h, status);
        __h = IceInternal.HashUtil.hashAdd(__h, receivers);
        return __h;
    }

    public ReceiverResult
    clone()
    {
        ReceiverResult c = null;
        try
        {
            c = (ReceiverResult)super.clone();
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
        ReceiverSeqHelper.write(__os, receivers);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        status = __is.readInt();
        receivers = ReceiverSeqHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, ReceiverResult __v)
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

    static public ReceiverResult
    __read(IceInternal.BasicStream __is, ReceiverResult __v)
    {
        if(__v == null)
        {
             __v = new ReceiverResult();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final ReceiverResult __nullMarshalValue = new ReceiverResult();

    public static final long serialVersionUID = -5884709200657580708L;
}
