package com.zhihu.matisse.sample;

import android.content.Context;

import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;

import java.util.Collections;
import java.util.Set;

public class UMPhotoFilter extends Filter {

    @Override
    protected Set<MimeType> constraintTypes() {
        return Collections.singleton(MimeType.JPEG);
    }

    @Override
    public IncapableCause filter(Context context, Item item) {
        if (!needFiltering(context, item))
            return null;

        if (false) {
            return new IncapableCause(IncapableCause.DIALOG, "照片不可选");
        }

        return null;
    }

    @Override
    public boolean isDisabled(Context context, Item item) {
        return item.mimeType.equals("image/jpeg");
    }
}