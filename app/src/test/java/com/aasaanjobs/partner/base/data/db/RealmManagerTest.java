package com.aasaanjobs.partner.base.data.db;

import android.content.Context;

import com.aasaanjobs.partner.base.data.db.tables.FunctionalAreaDO;
import com.aasaanjobs.partner.base.utils.FileUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by nazmuddinmavliwala on 24/05/16.
 */

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"org.mockito.*"
            ,"android.*"})
@PrepareForTest({Realm.class
        , RealmQuery.class
        , RealmConfiguration.class
        , RealmResults.class})
public class RealmManagerTest {

    private static final String PARTNER_APP = "AasaanjobsPartner.realm";
    private RealmManager realmManager;

    @Mock
    Context context;

    @Before
    public void setUp() throws Exception {
        mockStatic(Realm.class);
        Realm mockRealm = PowerMockito.mock(Realm.class);
        when(Realm.getDefaultInstance()).thenReturn(mockRealm);
        FileUtil fileUtil = mock(FileUtil.class);
        this.realmManager = new RealmManager(context, fileUtil);
    }

    @Test
    public void shouldBeAbleToGetDefaultInstance() {
        assertThat(Realm.getDefaultInstance(), is(realmManager.getRealm()));
    }

    @Test
    public void testInsert_AssertExecuteTransaction() throws Exception {
        FunctionalAreaDO input = new FunctionalAreaDO();
        when(realmManager.getRealm().createObject(FunctionalAreaDO.class))
                .thenReturn(input);

        FunctionalAreaDO outPut = realmManager.getRealm().createObject(FunctionalAreaDO.class);
        realmManager.insert(outPut);
        verify(realmManager.getRealm()).executeTransactionAsync(Mockito
                .any(Realm.Transaction.class));

    }

    @Test
    public void testInsert_AssertExecuteTransactionCallbacks() throws Exception {
        doCallRealMethod().when(realmManager.getRealm()).executeTransaction(Mockito
                .any(Realm.Transaction.class));
        FunctionalAreaDO input = new FunctionalAreaDO();
        when(realmManager.getRealm().createObject(FunctionalAreaDO.class))
                .thenReturn(input);

        FunctionalAreaDO outPut = realmManager.getRealm().createObject(FunctionalAreaDO.class);
        realmManager.insert(outPut);
        verify(realmManager.getRealm(),times(2)).executeTransactionAsync(Mockito
                .any(Realm.Transaction.class));
    }

    @Test
    public void testInsertAll() throws Exception {
        doCallRealMethod().when(realmManager.getRealm()).executeTransaction(Mockito
                .any(Realm.Transaction.class));
        FunctionalAreaDO input = new FunctionalAreaDO();
        when(realmManager.getRealm().createObject(FunctionalAreaDO.class))
                .thenReturn(input);

        FunctionalAreaDO outPut = realmManager.getRealm().createObject(FunctionalAreaDO.class);
        List<FunctionalAreaDO> list = new ArrayList<>();
        list.add(outPut);
        realmManager.insertAll(list);
        verify(realmManager.getRealm(),times(3)).executeTransactionAsync(Mockito
                .any(Realm.Transaction.class));
    }

    @Test
    public void testRead() throws Exception {
        FunctionalAreaDO input = new FunctionalAreaDO();
        RealmResults<FunctionalAreaDO> results = mock(RealmResults.class);
        when(results.add(input)).thenReturn(true);

        mockStatic(RealmQuery.class);
        RealmQuery<FunctionalAreaDO> query = PowerMockito.mock(RealmQuery.class);
        when(realmManager.getRealm().where(FunctionalAreaDO.class))
                .thenReturn(query);
        when(query.findAll()).thenReturn(results);

        RealmResults<FunctionalAreaDO> outputs = realmManager
                .read(FunctionalAreaDO.class);
        assertEquals(outputs,results);
    }

    @Test
    public void testReadFirst() throws Exception {
        FunctionalAreaDO input = new FunctionalAreaDO();
        RealmResults<FunctionalAreaDO> results = mock(RealmResults.class);
        when(results.add(input)).thenReturn(true);

        mockStatic(RealmQuery.class);
        RealmQuery<FunctionalAreaDO> query = PowerMockito.mock(RealmQuery.class);
        when(realmManager.getRealm().where(FunctionalAreaDO.class))
                .thenReturn(query);
        when(query.findFirst()).thenReturn(input);

        FunctionalAreaDO output = realmManager.readFirst(FunctionalAreaDO.class);
        assertEquals(output,input);
    }

    @Test
    public void testGetDataBaseName() throws Exception {
        mockStatic(RealmConfiguration.class);
        RealmConfiguration mockConfig = PowerMockito.mock(RealmConfiguration.class);
        when(mockConfig.getRealmFileName()).thenReturn(PARTNER_APP);
        when(realmManager.getRealm().getConfiguration())
                .thenReturn(mockConfig);

        assertEquals(realmManager.getDatabaseName(),PARTNER_APP);
    }
}