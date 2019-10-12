package com.hustacm1701.countbyheart;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.hustacm1701.countbyheart", appContext.getPackageName());
    }
//    @Test
//    public void testInfo(){
////      对info中是否能正常创建正确的level 和 tasknumber进行测试
//        Info info = Info.getInstance();
//        System.out.println(info.getLevel());
//        System.out.println(info.getTaskNumber());
//        info.setTaskNumber(50);
//        info.setLevel(3);
//        System.out.println(info.getLevel());
//        System.out.println(info.getTaskNumber());
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
//        Info newInfo = new Info(calendar);
//        System.out.println(newInfo.getLevel());
//        System.out.println(newInfo.getTaskNumber());
//        assertEquals(1,newInfo.getLevel());
//        assertEquals(30,newInfo.getTaskNumber());
//    }
}
