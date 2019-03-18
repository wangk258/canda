package com.canda.hbase.test;

import java.util.Date;

import com.canda.hbase.po.LctGpsMessagePO;
import com.chinaway.columnar.config.DataSourceConfig;
import com.chinaway.columnar.exception.ColumnarClientException;
import com.chinaway.columnar.hbase.HBaseColumnarClient;
import com.chinaway.columnar.hbase.HBaseSourceWrapper;

/**
 * @author Wangkun
 * @since 2019/2/27 4:35 PM
 */
public class HbaseTest {

    public static void main(String[] args) throws ColumnarClientException {
        HbaseTest test = new HbaseTest();
        test.putOne(test.getClient());
    }

    private void putOne(HBaseColumnarClient client) throws ColumnarClientException {
        LctGpsMessagePO po = new LctGpsMessagePO();
        po.setRowKey("3_107015291293119");
        po.setId(1L);
        po.setImei("ewweew");
        po.setCreatetime(new Date());
        client.putObject(po);
    }

    private HBaseColumnarClient getClient() throws ColumnarClientException {
        HBaseColumnarClient client = new HBaseColumnarClient(600, 500);
        DataSourceConfig config = new DataSourceConfig("hbase.properties");
        HBaseSourceWrapper wrapper = new HBaseSourceWrapper(config.getProperties());
        client.setHbaseSourceWrapper(wrapper);
        return client;
    }

}
