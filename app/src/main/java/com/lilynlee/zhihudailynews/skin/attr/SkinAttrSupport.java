package com.lilynlee.zhihudailynews.skin.attr;

import android.content.Context;
import android.util.AttributeSet;

import com.lilynlee.zhihudailynews.Const.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Lilynlee on 2017/3/6
 * 邮箱：1132860085@qq.com
 */

public class SkinAttrSupport {
    public static List<SkinAttr> getSkinAttrs(AttributeSet attrs, Context context){
        List<SkinAttr> mSkinAttrs = new ArrayList<SkinAttr>();
        SkinAttrType attrType = null;
        SkinAttr skinAttr = null;
        for (int i = 0,n=attrs.getAttributeCount();i<n;i++){
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            if (attrValue.startsWith("@")){
                int id = -1;
                try {
                    id = Integer.parseInt(attrValue.substring(1));
                } catch (NumberFormatException e) {
                }
                if (id == -1){
                    continue;
                }
                String resName = context.getResources().getResourceEntryName(id);
                if (resName.startsWith(Const.SKIN_PREFIX)){
                    attrType = getSupportAttrType(attrName);
                    if (attrType == null)
                        continue;
                    skinAttr = new SkinAttr(resName,attrType);
                    mSkinAttrs.add(skinAttr);
                }
            }
        }
        return mSkinAttrs;
    }

    private static SkinAttrType getSupportAttrType(String attrName) {
        for (SkinAttrType attrType : SkinAttrType.values()){
            if (attrType.getResType().equals(attrName)){
                return attrType;
            }
        }
        return null;
    }
}
