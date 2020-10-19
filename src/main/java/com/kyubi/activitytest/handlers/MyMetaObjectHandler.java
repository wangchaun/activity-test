package com.kyubi.activitytest.handlers;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.kyubi.activitytest.modules.constants.CommonConstants;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author Kyubi
 * @date 2020-09-28 18:20
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "createTime", new Date());
        this.fillStrategy(metaObject, "createPin", "system");
        this.fillStrategy(metaObject, "updatePin", "system");
        this.fillStrategy(metaObject, "ts", new Date());
        this.fillStrategy(metaObject, "version", 1L);
        this.fillStrategy(metaObject, "yn", CommonConstants.Yn.YES);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "ts", new Date());
    }
}
