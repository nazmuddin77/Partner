package com.aasaanjobs.partner.base.data.db;

import android.content.Context;

import com.aasaanjobs.partner.base.utils.FileUtil;
import com.aasaanjobs.partner.base.utils.StringUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by nazmuddinmavliwala on 25/05/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class RealmAssetHelperTest {

    @Mock
    private Context context;

    @Mock
    private FileUtil fileUtil;

    private RealmAssetHelper realmAssetHelper;

    @Before
    public void prepareForTest() {
        realmAssetHelper = spy(new RealmAssetHelper(context,fileUtil));

//        when(StringUtil.isEmpty(Mockito.anyString())).thenAnswer(new Answer<Boolean>() {
//            @Override
//            public Boolean answer(InvocationOnMock invocation) throws Throwable {
//                String string = (String) invocation.getArguments()[0];
//                return string == null || string.isEmpty();
//            }
//        });
        when(fileUtil.isDatabaseAssetExists(Mockito.anyString())).thenReturn(true);

        when(fileUtil.loadDatabaseToLocalStorage(Mockito.anyString()))
                .thenReturn(TestConstants.FILE_PATH);

        when(fileUtil.generateDatabaseFileName(Mockito.anyString()))
                .thenReturn(TestConstants.FILE_PATH);

        when(fileUtil.getFileNameForDatabase(Mockito.anyString()))
                .thenReturn(TestConstants.FILE_PATH);
        
    }

    @Test(expected = RuntimeException.class)
    public void onEmptyDatabaseName_shouldThrowRuntimeException()
            throws RuntimeException {
        realmAssetHelper.loadDatabaseToStorage(null, null);
    }

    @Test(expected = RuntimeException.class)
    public void onMissingDbAsset_shouldThrowRuntimeException()
            throws RuntimeException{
        when(fileUtil.isDatabaseAssetExists(TestConstants.DB_NAME))
                .thenReturn(false);

        realmAssetHelper.loadDatabaseToStorage(TestConstants.DB_NAME, null);
    }

    @Test
    public void onFreshInstall_databaseVersionShouldBeStored() {
        when(fileUtil.getCurrentDbVersion(Mockito.anyString()))
                .thenReturn(null);
        when(fileUtil.getAssetsDbVersion(Mockito.anyString()))
                .thenReturn(2);
        realmAssetHelper.loadDatabaseToStorage(TestConstants.DB_NAME, null);
        Mockito.verify(fileUtil, Mockito.times(1)).storeDatabaseVersion(2);
    }

    @Test
    public void onDatabaseUpdate_databaseVersionShouldBeStored() {
        when(fileUtil.getCurrentDbVersion(Mockito.anyString())).thenReturn(1);
        when(fileUtil.getAssetsDbVersion(Mockito.anyString())).thenReturn(2);

        realmAssetHelper.loadDatabaseToStorage(TestConstants.DB_NAME, null);

        Mockito.verify(fileUtil, Mockito.times(1)).storeDatabaseVersion(2);
    }

    @Test
    public void onFreshAppInstall_databaseShouldBeLoadedToInternalStorage() {
        when(fileUtil.getCurrentDbVersion(Mockito.anyString()))
                .thenReturn(null);
        realmAssetHelper.loadDatabaseToStorage(TestConstants.DB_NAME, null);
        Mockito.verify(fileUtil, Mockito.times(1)).loadDatabaseToLocalStorage(Mockito.anyString());
    }

    @Test
    public void onDatabaseUpdate_databaseShouldBeLoadedToInternalStorage() {
        when(fileUtil.getCurrentDbVersion(Mockito.anyString())).thenReturn(1);
        when(fileUtil.getAssetsDbVersion(Mockito.anyString())).thenReturn(2);

        realmAssetHelper.loadDatabaseToStorage(TestConstants.DB_NAME, null);

        Mockito.verify(fileUtil, Mockito.times(1)).loadDatabaseToLocalStorage(Mockito.anyString());
    }

    @Test
    public void onSameDatabaseVersionArrived_databaseShouldNotBeLoadedToInternalStorage() {
        when(fileUtil.getCurrentDbVersion(Mockito.anyString())).thenReturn(2);
        when(fileUtil.getAssetsDbVersion(Mockito.anyString())).thenReturn(2);

        realmAssetHelper.loadDatabaseToStorage(TestConstants.DB_NAME, null);
        Mockito.verify(fileUtil, Mockito.never()).loadDatabaseToLocalStorage(Mockito.anyString());
    }

    @Test
    public void onFreshAppInstall_relevantCallbackShouldBeTriggered() {
        RealmAssetHelperStorageListener listener = Mockito.mock(RealmAssetHelperStorageListener.class);
        when(fileUtil.getCurrentDbVersion(Mockito.anyString()))
                .thenReturn(null);
        realmAssetHelper.loadDatabaseToStorage(TestConstants.DB_NAME, listener);
        Mockito.verify(listener, Mockito.times(1)).onLoadedToStorage(TestConstants.FILE_PATH
                , RealmAssetHelperStatus.INSTALLED);
    }

    @Test
    public void onDatabaseUpdate_relevantCallbackShouldBeTriggered() {
        RealmAssetHelperStorageListener listener = Mockito.mock(RealmAssetHelperStorageListener.class);
        when(fileUtil.getCurrentDbVersion(Mockito.anyString()))
                .thenReturn(1);
        when(fileUtil.getAssetsDbVersion(Mockito.anyString()))
                .thenReturn(2);

        realmAssetHelper.loadDatabaseToStorage(TestConstants.DB_NAME, listener);

        Mockito.verify(listener, Mockito.times(1)).onLoadedToStorage(TestConstants.FILE_PATH
                , RealmAssetHelperStatus.UPDATED);
    }

    @Test
    public void onSameDatabaseVersionArrived_relevantCallbackShouldBeTriggered() {
        RealmAssetHelperStorageListener listener = Mockito.mock(RealmAssetHelperStorageListener.class);
        when(fileUtil.getCurrentDbVersion(Mockito.anyString()))
                .thenReturn(2);
        when(fileUtil.getAssetsDbVersion(Mockito.anyString()))
                .thenReturn(2);

        realmAssetHelper.loadDatabaseToStorage(TestConstants.DB_NAME, listener);
        Mockito.verify(listener, Mockito.times(1)).onLoadedToStorage(TestConstants.FILE_PATH
                , RealmAssetHelperStatus.IGNORED);
    }

    @Test
    public void onDatabaseUpdate_versionShouldBeSetForCorrectDatabase() {

        when(fileUtil.getCurrentDbVersion(Mockito.anyString())).thenReturn(1);
        when(fileUtil.getAssetsDbVersion(Mockito.anyString())).thenReturn(2);

        realmAssetHelper.loadDatabaseToStorage(TestConstants.DB_NAME, null);
        Mockito.verify(fileUtil, Mockito.times(1)).storeDatabaseVersion(2);
    }

    @Test(expected = RuntimeException.class)
    public void onLoadDatabaseToStorageFileNotFound_exceptionShouldBeThrown() {
        when(fileUtil.getCurrentDbVersion(Mockito.anyString())).thenReturn(1);
        when(fileUtil.getAssetsDbVersion(Mockito.anyString())).thenReturn(2);

        when(fileUtil.loadDatabaseToLocalStorage(Mockito.anyString()))
                .thenReturn(null);
        realmAssetHelper.loadDatabaseToStorage(TestConstants.DB_NAME, null);
    }
}