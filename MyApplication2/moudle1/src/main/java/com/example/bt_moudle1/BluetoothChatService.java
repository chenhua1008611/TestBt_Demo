//package com.example.bt_moudle1;
//
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothServerSocket;
//import android.bluetooth.BluetoothSocket;
//import android.content.Context;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.UUID;
///**
// * Created by admin on 2017/9/20.
// */
//
//public class BluetoothChatService {
//
//    private static final boolean D = true;
//    private static final UUID MY_UUID_INSECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
//    private static final UUID MY_UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
//    private static final String NAME_INSECURE = "BluetoothChatInsecure";
//    private static final String NAME_SECURE = "BluetoothChatSecure";
//    public static final int STATE_CONNECTED = 3;
//    public static final int STATE_CONNECTING = 2;
//    public static final int STATE_LISTEN = 1;
//    public static final int STATE_NONE = 0;
//    private final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
//    private ConnectThread mConnectThread;
//    private ConnectedThread mConnectedThread;
//    private final Handler mHandler;
//    private AcceptThread mInsecureAcceptThread;
//    private AcceptThread mSecureAcceptThread;
//    private int mState = 0;
//
//    public BluetoothChatService(Context paramContext, Handler paramHandler)
//    {
//        this.mHandler = paramHandler;
//    }
//
//    private void connected(BluetoothSocket paramBluetoothSocket, BluetoothDevice paramBluetoothDevice, String paramString)
//    {
//        try
//        {
//            Log.d("DEBUG_BLUETOOTH", String.format("%s [Socket Type : %s]", new Object[] { "Connected", paramString }));
//            if (this.mConnectThread != null)
//            {
//                this.mConnectThread.cancel();
//                this.mConnectThread = null;
//            }
//            if (this.mConnectedThread != null)
//            {
//                this.mConnectedThread.cancel();
//                this.mConnectedThread = null;
//            }
//            if (this.mSecureAcceptThread != null)
//            {
//                this.mSecureAcceptThread.cancel();
//                this.mSecureAcceptThread = null;
//            }
//            if (this.mInsecureAcceptThread != null)
//            {
//                this.mInsecureAcceptThread.cancel();
//                this.mInsecureAcceptThread = null;
//            }
//            this.mConnectedThread = new ConnectedThread(paramBluetoothSocket, paramString);
//            this.mConnectedThread.start();
//            paramBluetoothSocket = this.mHandler.obtainMessage(4);
//            paramString = new Bundle();
//            paramString.putString("DEVICE_NAME", paramBluetoothDevice.getName());
//            paramBluetoothSocket.setData(paramString);
//            this.mHandler.sendMessage(paramBluetoothSocket);
//            setState(3);
//        }
//        catch (Exception paramBluetoothSocket)
//        {
//            for (;;)
//            {
//                setState(0);
//            }
//        }
//        finally {}
//    }
//
//    private void connectionFailed()
//    {
//        Message localMessage = this.mHandler.obtainMessage(5);
//        Bundle localBundle = new Bundle();
//        localBundle.putInt("CONNECTION_INFO", 2131099697);
//        localMessage.setData(localBundle);
//        this.mHandler.sendMessage(localMessage);
//        start();
//    }
//
//    private void connectionLost()
//    {
//        Message localMessage = this.mHandler.obtainMessage(5);
//        Bundle localBundle = new Bundle();
//        localBundle.putInt("CONNECTION_INFO", 2131099698);
//        localMessage.setData(localBundle);
//        this.mHandler.sendMessage(localMessage);
//        start();
//    }
//
//    private void setState(int paramInt)
//    {
//        try
//        {
//            Log.d("DEBUG_BLUETOOTH", "State : " + this.mState + " -> " + paramInt);
//            this.mState = paramInt;
//            this.mHandler.obtainMessage(1, paramInt, -1).sendToTarget();
//            return;
//        }
//        finally
//        {
//            localObject = finally;
//            throw ((Throwable)localObject);
//        }
//    }
//
//    /* Error */
//    public void connect(BluetoothDevice paramBluetoothDevice, boolean paramBoolean)
//    {
//        // Byte code:
//        //   0: aload_0
//        //   1: monitorenter
//        //   2: ldc 109
//        //   4: new 188	java/lang/StringBuilder
//        //   7: dup
//        //   8: invokespecial 189	java/lang/StringBuilder:<init>	()V
//        //   11: ldc -43
//        //   13: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
//        //   16: aload_1
//        //   17: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
//        //   20: invokevirtual 203	java/lang/StringBuilder:toString	()Ljava/lang/String;
//        //   23: invokestatic 125	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
//        //   26: pop
//        //   27: aload_0
//        //   28: getfield 75	product/com/bt/bt_ceab2/BluetoothChatService:mState	I
//        //   31: iconst_2
//        //   32: if_icmpne +22 -> 54
//        //   35: aload_0
//        //   36: getfield 99	product/com/bt/bt_ceab2/BluetoothChatService:mConnectThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;
//        //   39: ifnull +15 -> 54
//        //   42: aload_0
//        //   43: getfield 99	product/com/bt/bt_ceab2/BluetoothChatService:mConnectThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;
//        //   46: invokevirtual 128	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:cancel	()V
//        //   49: aload_0
//        //   50: aconst_null
//        //   51: putfield 99	product/com/bt/bt_ceab2/BluetoothChatService:mConnectThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;
//        //   54: aload_0
//        //   55: getfield 130	product/com/bt/bt_ceab2/BluetoothChatService:mConnectedThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectedThread;
//        //   58: ifnull +15 -> 73
//        //   61: aload_0
//        //   62: getfield 130	product/com/bt/bt_ceab2/BluetoothChatService:mConnectedThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectedThread;
//        //   65: invokevirtual 131	product/com/bt/bt_ceab2/BluetoothChatService$ConnectedThread:cancel	()V
//        //   68: aload_0
//        //   69: aconst_null
//        //   70: putfield 130	product/com/bt/bt_ceab2/BluetoothChatService:mConnectedThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectedThread;
//        //   73: aload_0
//        //   74: new 9	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread
//        //   77: dup
//        //   78: aload_0
//        //   79: aload_1
//        //   80: iload_2
//        //   81: invokespecial 219	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:<init>	(Lproduct/com/bt/bt_ceab2/BluetoothChatService;Landroid/bluetooth/BluetoothDevice;Z)V
//        //   84: putfield 99	product/com/bt/bt_ceab2/BluetoothChatService:mConnectThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;
//        //   87: aload_0
//        //   88: getfield 99	product/com/bt/bt_ceab2/BluetoothChatService:mConnectThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;
//        //   91: invokevirtual 220	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:start	()V
//        //   94: aload_0
//        //   95: iconst_2
//        //   96: invokespecial 177	product/com/bt/bt_ceab2/BluetoothChatService:setState	(I)V
//        //   99: aload_0
//        //   100: monitorexit
//        //   101: return
//        //   102: astore_1
//        //   103: aload_0
//        //   104: monitorexit
//        //   105: aload_1
//        //   106: athrow
//        //   107: astore_1
//        //   108: goto -9 -> 99
//        // Local variable table:
//        //   start	length	slot	name	signature
//        //   0	111	0	this	BluetoothChatService
//        //   0	111	1	paramBluetoothDevice	BluetoothDevice
//        //   0	111	2	paramBoolean	boolean
//        // Exception table:
//        //   from	to	target	type
//        //   2	54	102	finally
//        //   54	73	102	finally
//        //   73	99	102	finally
//        //   2	54	107	java/lang/Exception
//        //   54	73	107	java/lang/Exception
//        //   73	99	107	java/lang/Exception
//    }
//
//    public int getState()
//    {
//        try
//        {
//            int i = this.mState;
//            return i;
//        }
//        finally
//        {
//            localObject = finally;
//            throw ((Throwable)localObject);
//        }
//    }
//
//    /* Error */
//    public void start()
//    {
//        // Byte code:
//        //   0: aload_0
//        //   1: monitorenter
//        //   2: ldc 109
//        //   4: ldc -32
//        //   6: invokestatic 125	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
//        //   9: pop
//        //   10: aload_0
//        //   11: getfield 99	product/com/bt/bt_ceab2/BluetoothChatService:mConnectThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;
//        //   14: ifnull +15 -> 29
//        //   17: aload_0
//        //   18: getfield 99	product/com/bt/bt_ceab2/BluetoothChatService:mConnectThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;
//        //   21: invokevirtual 128	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:cancel	()V
//        //   24: aload_0
//        //   25: aconst_null
//        //   26: putfield 99	product/com/bt/bt_ceab2/BluetoothChatService:mConnectThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;
//        //   29: aload_0
//        //   30: getfield 130	product/com/bt/bt_ceab2/BluetoothChatService:mConnectedThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectedThread;
//        //   33: ifnull +15 -> 48
//        //   36: aload_0
//        //   37: getfield 130	product/com/bt/bt_ceab2/BluetoothChatService:mConnectedThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectedThread;
//        //   40: invokevirtual 131	product/com/bt/bt_ceab2/BluetoothChatService$ConnectedThread:cancel	()V
//        //   43: aload_0
//        //   44: aconst_null
//        //   45: putfield 130	product/com/bt/bt_ceab2/BluetoothChatService:mConnectedThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectedThread;
//        //   48: aload_0
//        //   49: iconst_1
//        //   50: invokespecial 177	product/com/bt/bt_ceab2/BluetoothChatService:setState	(I)V
//        //   53: aload_0
//        //   54: getfield 133	product/com/bt/bt_ceab2/BluetoothChatService:mSecureAcceptThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$AcceptThread;
//        //   57: ifnonnull +23 -> 80
//        //   60: aload_0
//        //   61: new 6	product/com/bt/bt_ceab2/BluetoothChatService$AcceptThread
//        //   64: dup
//        //   65: aload_0
//        //   66: iconst_1
//        //   67: invokespecial 227	product/com/bt/bt_ceab2/BluetoothChatService$AcceptThread:<init>	(Lproduct/com/bt/bt_ceab2/BluetoothChatService;Z)V
//        //   70: putfield 133	product/com/bt/bt_ceab2/BluetoothChatService:mSecureAcceptThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$AcceptThread;
//        //   73: aload_0
//        //   74: getfield 133	product/com/bt/bt_ceab2/BluetoothChatService:mSecureAcceptThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$AcceptThread;
//        //   77: invokevirtual 228	product/com/bt/bt_ceab2/BluetoothChatService$AcceptThread:start	()V
//        //   80: aload_0
//        //   81: getfield 136	product/com/bt/bt_ceab2/BluetoothChatService:mInsecureAcceptThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$AcceptThread;
//        //   84: ifnonnull +23 -> 107
//        //   87: aload_0
//        //   88: new 6	product/com/bt/bt_ceab2/BluetoothChatService$AcceptThread
//        //   91: dup
//        //   92: aload_0
//        //   93: iconst_0
//        //   94: invokespecial 227	product/com/bt/bt_ceab2/BluetoothChatService$AcceptThread:<init>	(Lproduct/com/bt/bt_ceab2/BluetoothChatService;Z)V
//        //   97: putfield 136	product/com/bt/bt_ceab2/BluetoothChatService:mInsecureAcceptThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$AcceptThread;
//        //   100: aload_0
//        //   101: getfield 136	product/com/bt/bt_ceab2/BluetoothChatService:mInsecureAcceptThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$AcceptThread;
//        //   104: invokevirtual 228	product/com/bt/bt_ceab2/BluetoothChatService$AcceptThread:start	()V
//        //   107: aload_0
//        //   108: monitorexit
//        //   109: return
//        //   110: astore_1
//        //   111: aload_0
//        //   112: monitorexit
//        //   113: aload_1
//        //   114: athrow
//        //   115: astore_1
//        //   116: goto -9 -> 107
//        // Local variable table:
//        //   start	length	slot	name	signature
//        //   0	119	0	this	BluetoothChatService
//        //   110	4	1	localObject	Object
//        //   115	1	1	localException	Exception
//        // Exception table:
//        //   from	to	target	type
//        //   2	29	110	finally
//        //   29	48	110	finally
//        //   48	80	110	finally
//        //   80	107	110	finally
//        //   2	29	115	java/lang/Exception
//        //   29	48	115	java/lang/Exception
//        //   48	80	115	java/lang/Exception
//        //   80	107	115	java/lang/Exception
//    }
//
//    /* Error */
//    public void stop()
//    {
//        // Byte code:
//        //   0: aload_0
//        //   1: monitorenter
//        //   2: ldc 109
//        //   4: ldc -25
//        //   6: invokestatic 125	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
//        //   9: pop
//        //   10: aload_0
//        //   11: getfield 99	product/com/bt/bt_ceab2/BluetoothChatService:mConnectThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;
//        //   14: ifnull +15 -> 29
//        //   17: aload_0
//        //   18: getfield 99	product/com/bt/bt_ceab2/BluetoothChatService:mConnectThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;
//        //   21: invokevirtual 128	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:cancel	()V
//        //   24: aload_0
//        //   25: aconst_null
//        //   26: putfield 99	product/com/bt/bt_ceab2/BluetoothChatService:mConnectThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;
//        //   29: aload_0
//        //   30: getfield 130	product/com/bt/bt_ceab2/BluetoothChatService:mConnectedThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectedThread;
//        //   33: ifnull +15 -> 48
//        //   36: aload_0
//        //   37: getfield 130	product/com/bt/bt_ceab2/BluetoothChatService:mConnectedThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectedThread;
//        //   40: invokevirtual 131	product/com/bt/bt_ceab2/BluetoothChatService$ConnectedThread:cancel	()V
//        //   43: aload_0
//        //   44: aconst_null
//        //   45: putfield 130	product/com/bt/bt_ceab2/BluetoothChatService:mConnectedThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectedThread;
//        //   48: aload_0
//        //   49: getfield 133	product/com/bt/bt_ceab2/BluetoothChatService:mSecureAcceptThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$AcceptThread;
//        //   52: ifnull +15 -> 67
//        //   55: aload_0
//        //   56: getfield 133	product/com/bt/bt_ceab2/BluetoothChatService:mSecureAcceptThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$AcceptThread;
//        //   59: invokevirtual 134	product/com/bt/bt_ceab2/BluetoothChatService$AcceptThread:cancel	()V
//        //   62: aload_0
//        //   63: aconst_null
//        //   64: putfield 133	product/com/bt/bt_ceab2/BluetoothChatService:mSecureAcceptThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$AcceptThread;
//        //   67: aload_0
//        //   68: getfield 136	product/com/bt/bt_ceab2/BluetoothChatService:mInsecureAcceptThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$AcceptThread;
//        //   71: ifnull +15 -> 86
//        //   74: aload_0
//        //   75: getfield 136	product/com/bt/bt_ceab2/BluetoothChatService:mInsecureAcceptThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$AcceptThread;
//        //   78: invokevirtual 134	product/com/bt/bt_ceab2/BluetoothChatService$AcceptThread:cancel	()V
//        //   81: aload_0
//        //   82: aconst_null
//        //   83: putfield 136	product/com/bt/bt_ceab2/BluetoothChatService:mInsecureAcceptThread	Lproduct/com/bt/bt_ceab2/BluetoothChatService$AcceptThread;
//        //   86: aload_0
//        //   87: iconst_0
//        //   88: invokespecial 177	product/com/bt/bt_ceab2/BluetoothChatService:setState	(I)V
//        //   91: aload_0
//        //   92: monitorexit
//        //   93: return
//        //   94: astore_1
//        //   95: aload_0
//        //   96: monitorexit
//        //   97: aload_1
//        //   98: athrow
//        //   99: astore_1
//        //   100: goto -9 -> 91
//        // Local variable table:
//        //   start	length	slot	name	signature
//        //   0	103	0	this	BluetoothChatService
//        //   94	4	1	localObject	Object
//        //   99	1	1	localException	Exception
//        // Exception table:
//        //   from	to	target	type
//        //   2	29	94	finally
//        //   29	48	94	finally
//        //   48	67	94	finally
//        //   67	86	94	finally
//        //   86	91	94	finally
//        //   2	29	99	java/lang/Exception
//        //   29	48	99	java/lang/Exception
//        //   48	67	99	java/lang/Exception
//        //   67	86	99	java/lang/Exception
//        //   86	91	99	java/lang/Exception
//    }
//
//    public void write(byte[] paramArrayOfByte)
//    {
//        try
//        {
//            if (this.mState != 3) {
//                return;
//            }
//            ConnectedThread localConnectedThread = this.mConnectedThread;
//            localConnectedThread.write(paramArrayOfByte);
//            return;
//        }
//        finally {}
//    }
//
//    private class AcceptThread
//            extends Thread
//    {
//        private final String mSocketType;
//        private final BluetoothServerSocket mmServerSocket;
//
//        public AcceptThread(boolean paramBoolean)
//        {
//            Object localObject = null;
//            String str;
//            if (paramBoolean) {
//                str = "Secure";
//            }
//            for (;;)
//            {
//                this.mSocketType = str;
//                if (paramBoolean) {}
//                try
//                {
//                    for (this$1 = BluetoothChatService.this.mAdapter.listenUsingRfcommWithServiceRecord("BluetoothChatSecure", BluetoothChatService.MY_UUID_SECURE);; this$1 = BluetoothChatService.this.mAdapter.listenUsingInsecureRfcommWithServiceRecord("BluetoothChatInsecure", BluetoothChatService.MY_UUID_INSECURE))
//                    {
//                        this.mmServerSocket = BluetoothChatService.this;
//                        return;
//                        str = "Insecure";
//                        break;
//                    }
//                }
//                catch (IOException this$1)
//                {
//                    for (;;)
//                    {
//                        Log.d("DEBUG_BLUETOOTH", String.format("%s [Socket Type : %s]", new Object[] { "Listen() failed", this.mSocketType }), BluetoothChatService.this);
//                        this$1 = (BluetoothChatService)localObject;
//                    }
//                }
//            }
//        }
//
//        public void cancel()
//        {
//            Log.d("DEBUG_BLUETOOTH", String.format("%s [Socket Type : %s]", new Object[] { "Cancel " + this, this.mSocketType }));
//            try
//            {
//                this.mmServerSocket.close();
//                return;
//            }
//            catch (IOException localIOException)
//            {
//                Log.d("DEBUG_BLUETOOTH", String.format("%s [Socket Type : %s]", new Object[] { "Close() of server failed", this.mSocketType }), localIOException);
//            }
//        }
//
//        public void run()
//        {
//            Log.d("DEBUG_BLUETOOTH", String.format("%s [Socket Type : %s]", new Object[] { "Begin mAcceptThread" + this, this.mSocketType }));
//            setName("AcceptThread" + this.mSocketType);
//            while (BluetoothChatService.this.mState != 3) {
//                for (;;)
//                {
//                    try
//                    {
//                        BluetoothSocket localBluetoothSocket1 = this.mmServerSocket.accept();
//                        if (localBluetoothSocket1 == null) {
//                            break;
//                        }
//                    }
//                    catch (IOException localIOException1)
//                    {
//                        Log.d("DEBUG_BLUETOOTH", String.format("%s [Socket Type : %s]", new Object[] { "Accept() failed", this.mSocketType }), localIOException1);
//                        Log.d("DEBUG_BLUETOOTH", String.format("%s [Socket Type : %s]", new Object[] { "End AcceptThread", this.mSocketType }));
//                        return;
//                    }
//                    catch (Exception localException)
//                    {
//                        Log.d("DEBUG_BLUETOOTH", String.format("%s [Socket Type : %s]", new Object[] { "Create a listening failed", this.mSocketType }), localException);
//                        continue;
//                        BluetoothChatService.this.connected(localBluetoothSocket2, localBluetoothSocket2.getRemoteDevice(), this.mSocketType);
//                        continue;
//                        try
//                        {
//                            localBluetoothSocket2.close();
//                        }
//                        catch (IOException localIOException2)
//                        {
//                            Log.e("DEBUG_BLUETOOTH", "Could not close unwanted socket", localIOException2);
//                        }
//                        continue;
//                        continue;
//                    }
//                    synchronized (BluetoothChatService.this)
//                    {
//                        switch (BluetoothChatService.this.mState)
//                        {
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private class ConnectThread
//            extends Thread
//    {
//        private final String mSocketType;
//        private final BluetoothDevice mmDevice;
//        private final BluetoothSocket mmSocket;
//
//        public ConnectThread(BluetoothDevice paramBluetoothDevice, boolean paramBoolean)
//        {
//            this.mmDevice = paramBluetoothDevice;
//            Object localObject = null;
//            if (paramBoolean) {
//                this$1 = "Secure";
//            }
//            for (;;)
//            {
//                this.mSocketType = BluetoothChatService.this;
//                if (paramBoolean) {}
//                try
//                {
//                    for (this$1 = paramBluetoothDevice.createRfcommSocketToServiceRecord(BluetoothChatService.MY_UUID_SECURE);; this$1 = paramBluetoothDevice.createInsecureRfcommSocketToServiceRecord(BluetoothChatService.MY_UUID_INSECURE))
//                    {
//                        this.mmSocket = BluetoothChatService.this;
//                        return;
//                        this$1 = "Insecure";
//                        break;
//                    }
//                }
//                catch (IOException this$1)
//                {
//                    for (;;)
//                    {
//                        Log.d("DEBUG_BLUETOOTH", String.format("%s [Socket Type : %s]", new Object[] { "Create() failed", this.mSocketType }), BluetoothChatService.this);
//                        this$1 = (BluetoothChatService)localObject;
//                    }
//                }
//            }
//        }
//
//        public void cancel()
//        {
//            try
//            {
//                this.mmSocket.close();
//                return;
//            }
//            catch (IOException localIOException)
//            {
//                Log.d("DEBUG_BLUETOOTH", String.format("%s [Socket Type : %s]", new Object[] { "Close() of connect, Socket failed", this.mSocketType }));
//            }
//        }
//
//        /* Error */
//        public void run()
//        {
//            // Byte code:
//            //   0: ldc 52
//            //   2: ldc 54
//            //   4: iconst_2
//            //   5: anewarray 56	java/lang/Object
//            //   8: dup
//            //   9: iconst_0
//            //   10: ldc 85
//            //   12: aastore
//            //   13: dup
//            //   14: iconst_1
//            //   15: aload_0
//            //   16: getfield 30	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:mSocketType	Ljava/lang/String;
//            //   19: aastore
//            //   20: invokestatic 64	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
//            //   23: invokestatic 82	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
//            //   26: pop
//            //   27: aload_0
//            //   28: new 87	java/lang/StringBuilder
//            //   31: dup
//            //   32: invokespecial 88	java/lang/StringBuilder:<init>	()V
//            //   35: ldc 89
//            //   37: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
//            //   40: aload_0
//            //   41: getfield 30	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:mSocketType	Ljava/lang/String;
//            //   44: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
//            //   47: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
//            //   50: invokevirtual 101	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:setName	(Ljava/lang/String;)V
//            //   53: aload_0
//            //   54: getfield 21	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:this$0	Lproduct/com/bt/bt_ceab2/BluetoothChatService;
//            //   57: invokestatic 105	product/com/bt/bt_ceab2/BluetoothChatService:access$100	(Lproduct/com/bt/bt_ceab2/BluetoothChatService;)Landroid/bluetooth/BluetoothAdapter;
//            //   60: invokevirtual 111	android/bluetooth/BluetoothAdapter:cancelDiscovery	()Z
//            //   63: pop
//            //   64: aload_0
//            //   65: getfield 42	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:mmSocket	Landroid/bluetooth/BluetoothSocket;
//            //   68: invokevirtual 114	android/bluetooth/BluetoothSocket:connect	()V
//            //   71: aload_0
//            //   72: getfield 21	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:this$0	Lproduct/com/bt/bt_ceab2/BluetoothChatService;
//            //   75: astore_1
//            //   76: aload_1
//            //   77: monitorenter
//            //   78: aload_0
//            //   79: getfield 21	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:this$0	Lproduct/com/bt/bt_ceab2/BluetoothChatService;
//            //   82: aconst_null
//            //   83: invokestatic 118	product/com/bt/bt_ceab2/BluetoothChatService:access$602	(Lproduct/com/bt/bt_ceab2/BluetoothChatService;Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;)Lproduct/com/bt/bt_ceab2/BluetoothChatService$ConnectThread;
//            //   86: pop
//            //   87: aload_1
//            //   88: monitorexit
//            //   89: aload_0
//            //   90: getfield 21	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:this$0	Lproduct/com/bt/bt_ceab2/BluetoothChatService;
//            //   93: aload_0
//            //   94: getfield 42	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:mmSocket	Landroid/bluetooth/BluetoothSocket;
//            //   97: aload_0
//            //   98: getfield 26	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:mmDevice	Landroid/bluetooth/BluetoothDevice;
//            //   101: aload_0
//            //   102: getfield 30	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:mSocketType	Ljava/lang/String;
//            //   105: invokestatic 122	product/com/bt/bt_ceab2/BluetoothChatService:access$400	(Lproduct/com/bt/bt_ceab2/BluetoothChatService;Landroid/bluetooth/BluetoothSocket;Landroid/bluetooth/BluetoothDevice;Ljava/lang/String;)V
//            //   108: return
//            //   109: astore_1
//            //   110: aload_0
//            //   111: getfield 42	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:mmSocket	Landroid/bluetooth/BluetoothSocket;
//            //   114: invokevirtual 77	android/bluetooth/BluetoothSocket:close	()V
//            //   117: aload_0
//            //   118: getfield 21	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:this$0	Lproduct/com/bt/bt_ceab2/BluetoothChatService;
//            //   121: invokestatic 126	product/com/bt/bt_ceab2/BluetoothChatService:access$500	(Lproduct/com/bt/bt_ceab2/BluetoothChatService;)V
//            //   124: return
//            //   125: astore_1
//            //   126: ldc 52
//            //   128: ldc 54
//            //   130: iconst_2
//            //   131: anewarray 56	java/lang/Object
//            //   134: dup
//            //   135: iconst_0
//            //   136: ldc -128
//            //   138: aastore
//            //   139: dup
//            //   140: iconst_1
//            //   141: aload_0
//            //   142: getfield 30	product/com/bt/bt_ceab2/BluetoothChatService$ConnectThread:mSocketType	Ljava/lang/String;
//            //   145: aastore
//            //   146: invokestatic 64	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
//            //   149: aload_1
//            //   150: invokestatic 70	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
//            //   153: pop
//            //   154: goto -37 -> 117
//            //   157: astore_2
//            //   158: aload_1
//            //   159: monitorexit
//            //   160: aload_2
//            //   161: athrow
//            // Local variable table:
//            //   start	length	slot	name	signature
//            //   0	162	0	this	ConnectThread
//            //   109	1	1	localIOException1	IOException
//            //   125	34	1	localIOException2	IOException
//            //   157	4	2	localObject	Object
//            // Exception table:
//            //   from	to	target	type
//            //   64	71	109	java/io/IOException
//            //   110	117	125	java/io/IOException
//            //   78	89	157	finally
//            //   158	160	157	finally
//        }
//    }
//
//    private class ConnectedThread
//            extends Thread
//    {
//        private final InputStream mmInStream;
//        private final OutputStream mmOutStream;
//        private final BluetoothSocket mmSocket;
//
//        public ConnectedThread(BluetoothSocket paramBluetoothSocket, String paramString)
//        {
//            Log.d("DEBUG_BLUETOOTH", String.format("%s [Socket Type : %s]", new Object[] { "Create ConnectedThread", paramString }));
//            this.mmSocket = paramBluetoothSocket;
//            this$1 = null;
//            localObject = null;
//            try
//            {
//                paramString = paramBluetoothSocket.getInputStream();
//                this$1 = paramString;
//                paramBluetoothSocket = paramBluetoothSocket.getOutputStream();
//                this$1 = paramString;
//            }
//            catch (IOException paramBluetoothSocket)
//            {
//                for (;;)
//                {
//                    Log.d("DEBUG_BLUETOOTH", "temp sockets not created", paramBluetoothSocket);
//                    paramBluetoothSocket = (BluetoothSocket)localObject;
//                }
//            }
//            this.mmInStream = BluetoothChatService.this;
//            this.mmOutStream = paramBluetoothSocket;
//        }
//
//        public void cancel()
//        {
//            try
//            {
//                this.mmSocket.close();
//                return;
//            }
//            catch (IOException localIOException)
//            {
//                Log.d("DEBUG_BLUETOOTH", "Close() of connect socket failed", localIOException);
//            }
//        }
//
//        public void run()
//        {
//            Log.d("DEBUG_BLUETOOTH", "Begin ConnectedThread");
//            byte[] arrayOfByte = new byte[5];
//            try
//            {
//                for (;;)
//                {
//                    int i = this.mmInStream.read(arrayOfByte, 0, 5);
//                    BluetoothChatService.this.mHandler.obtainMessage(2, i, -1, arrayOfByte).sendToTarget();
//                }
//                return;
//            }
//            catch (IOException localIOException)
//            {
//                Log.d("DEBUG_BLUETOOTH", "Disconnected", localIOException);
//                BluetoothChatService.this.connectionLost();
//            }
//        }
//
//        public void write(byte[] paramArrayOfByte)
//        {
//            try
//            {
//                this.mmOutStream.write(paramArrayOfByte);
//                BluetoothChatService.this.mHandler.obtainMessage(3, -1, -1, paramArrayOfByte).sendToTarget();
//                return;
//            }
//            catch (IOException paramArrayOfByte)
//            {
//                Log.d("DEBUG_BLUETOOTH", "Exception during write", paramArrayOfByte);
//            }
//        }
//    }
//}
//
//
