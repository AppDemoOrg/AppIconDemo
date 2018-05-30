package com.abt.appicon;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv) ImageView imageView;
    @OnClick(R.id.tv_festival) void festival() {
        switchIcon(IconConstant.FESTIVAL_ICON);
        imageView.setBackgroundResource(R.mipmap.icon_festival);
        int badgeCount = 1;
        ShortcutBadger.applyCount(MainActivity.this, badgeCount);
        //地址是https://github.com/leolin310148/ShortcutBadger
    }

    @OnClick(R.id.tv_normal) void normal() {
        switchIcon(IconConstant.NORMAL_ICON);
        imageView.setBackgroundResource(R.mipmap.icon_normal);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void switchIcon(int useCode) {
        try {
            if (useCode == IconConstant.UNUSED_ICON) return;

            PackageManager pm = getPackageManager();
            ComponentName normalComponentName = new ComponentName(getBaseContext(), IconConstant.FESTIVAL_ICON_TAG);
            int normalNewState = (useCode == IconConstant.NORMAL_ICON) ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                    : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
            if (pm.getComponentEnabledSetting(normalComponentName) != normalNewState) {
                pm.setComponentEnabledSetting(normalComponentName, normalNewState, PackageManager.DONT_KILL_APP);
                // 修改清单文件中activity-alias下的android:enable的值
            }

            ComponentName actComponentName = new ComponentName(getBaseContext(), IconConstant.NORMAL_ICON_TAG);
            int actNewState = (useCode == IconConstant.FESTIVAL_ICON) ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                    : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
            if (pm.getComponentEnabledSetting(actComponentName) != actNewState) {
                pm.setComponentEnabledSetting(actComponentName, actNewState, PackageManager.DONT_KILL_APP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
