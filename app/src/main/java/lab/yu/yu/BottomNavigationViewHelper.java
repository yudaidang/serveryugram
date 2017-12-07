package lab.yu.yu;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import java.lang.reflect.Field;

public class BottomNavigationViewHelper {
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                // set once again checked value, so view will be updated 
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                item.setPadding(0,25,0,0);
                item.setChecked(item.getItemData().isChecked());

            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
    public static void enableNavigation(final Context context, BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.ic_house: //ACTIVITY_NUM = 0
                        Intent intent = new Intent(context, AlertActivity.class);
                        context.startActivity(intent);
                        break;
                    case R.id.ic_search: //ACTIVITY_NUM = 1
                        Intent intent1 = new Intent(context, SearchActivity.class);
                        context.startActivity(intent1);
                        break;
                    case R.id.ic_me: //ACTIVITY_NUM = 4
                        Intent intent2 = new Intent(context, MeActivity.class);
                        context.startActivity(intent2);
                        break;
                    case R.id.ic_alert: //ACTIVITY_NUM = 3
                        Intent intent3 = new Intent(context, AlertActivity.class);
                        context.startActivity(intent3);
                        break;

                }

                return false;
            }
        });
    }
} 