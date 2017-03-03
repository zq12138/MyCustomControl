package com.example.admin.mycustomcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admin.mycustomcontrol.activity.ArcProgressBarActivity;
import com.example.admin.mycustomcontrol.activity.WedViewActivity;
import com.sumavision.callback.onCallBackListener;
import com.sumavision.sdk.SumaPaySDK;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements onCallBackListener {


    @BindView(R.id.btn_arcProgressBar)
    Button btnArcProgressBar;
    @BindView(R.id.btn_banner)
    Button btnBanner;
    String envFlag = "3";
    String mechantCode = "fbp100057";
    String[] business = new String[]{"hsbp2pMobile"};
    Map<String, String> map;
    SumaPaySDK sumaPaySDK;
    @BindView(R.id.btn_wed)
    Button btnWed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        sumaPaySDK = SumaPaySDK.defaultService();
//        sumaPaySDK.initService(this, mechantCode, business, envFlag, new onInitCallBackListener() {
//            @Override
//            public void receiveDataFromInit(String s) {
//                Log.i("tag", s + "---");
//            }
//        });
//        map = new HashMap<>();
//        map.put("TRXDATE", "20170301");//	交易日期
//        map.put("TRXTIME", "164034");    //	交易时间
//        map.put("COINSTCODE", "000124");    //	合作单位编号
//        map.put("COINSTCHANNEL", "000005");    //	合作单位渠道
//        map.put("SEQNO", "170301164034353127");    //	徽商交易流水号
//        map.put("KEYTYPE", "01");    //	证件类型
//        map.put("IDNO", "1111111111111111111111111111");    //	证件号码
//        map.put("SURNAME", "zq");    //	姓名
//        map.put("MOBILE", "15054396932");    //	银行预留手机号
//        map.put("PRODUCT", "31");    //	卡产品编号
//        map.put("SMSFLAG", "1");//		是否开通短信通知
//        map.put("RISK_YN", "1");//	风险评估标志
//        map.put("ACC_TYPE", "1");    //	账户类型
//        map.put("BINDCARDNO", "21212311215132121");    //	绑定卡号
//        map.put("GENDER", "M");    //	性别
//        map.put("ADNO", "pu9001902100");    //	推荐人代码
//        map.put("REQUEST_ID", "FB100011703011640343536889");    //	丰付请求流水号
//        map.put("BANK_CODE", "icbc");    //	银行编码
//        map.put("NOTICE_URL", "http://localhost/depository/asynCallBack");    //	异步通知地址
//        map.put("MERCHANT_CODE", mechantCode);    //	商户编码
//        map.put("SIGNATURE", "");    //	数字签名
//        map.put("USNO", "");    //	第三方平台用户编号
//        map.put("USR_IP", "");    //	客户IP
//        map.put("RESERVED", "");    //	保留域


    }

    Intent mIntent;


    @Override
    public void receiveDataFromPayment(String s) {
        Log.i("tag", s + "-------------");
    }


    @OnClick({R.id.btn_arcProgressBar, R.id.btn_banner, R.id.btn_wed})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_arcProgressBar:
                mIntent = new Intent(MainActivity.this, ArcProgressBarActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_banner:
                break;
            case R.id.btn_wed:
                mIntent = new Intent(MainActivity.this, WedViewActivity.class);
                startActivity(mIntent);
                break;
        }
    }
}
