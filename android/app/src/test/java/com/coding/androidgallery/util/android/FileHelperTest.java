package com.coding.androidgallery.util.android;

import android.content.Context;
import android.graphics.Bitmap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileOutputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mudassar Hussain on 5/30/2019.
 */
public class FileHelperTest {
    @Mock
    Context context;

    @Mock
    FileOutputStream outputStream;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveBitmapToFile_ShouldWriteToFile(){
        try{
            File mockFile = new File("app/cache");
            File mockFile1 = new File("app/cache/camera-captured.png");
            when(context.getCacheDir()).thenReturn(mockFile);
            when(new File(mockFile,anyString())).thenReturn(mockFile1);
            when(new FileOutputStream(mockFile1)).thenReturn(outputStream);
            FileHelper.saveBitmapToFile(context,any(Bitmap.class));

            verify(context).getCacheDir();
            verify(outputStream).write(any(byte[].class));
            verify(outputStream).flush();
            verify(outputStream).close();

        }catch (Exception ex){

        }
    }
}
