package com.mww.gecco;

import com.geccocrawler.gecco.annotation.FieldRenderName;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import com.geccocrawler.gecco.spider.SpiderBean;
import com.geccocrawler.gecco.spider.render.CustomFieldRender;
import com.geccocrawler.gecco.utils.ReflectUtils;
import net.sf.cglib.beans.BeanMap;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/12/22.
 */
@FieldRenderName("limitRender")
public class LimitRender implements CustomFieldRender {

    /**
     * @param request  HttpRequest
     * @param response HttpResponse
     * @param beanMap  将Field放入SpiderBean
     * @param bean     已经注入后的SpiderBean
     * @param field    需要注入的Field
     */
    @Override
    public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean, Field field) {
        Set<Field> limtFields = ReflectionUtils.getAllFields(bean.getClass(),
                ReflectionUtils.withAnnotation(Limit.class));
        for (Field limtField : limtFields) {
            Limit limit = limtField.getAnnotation(Limit.class);
            Object o = beanMap.get(limtField.getName());
            if (o == null) {
                continue;
            }
            boolean isList = ReflectUtils.haveSuperType(o.getClass(), List.class);// 是List类型
            if (isList) {
                List<String> list = (List<String>) o;
                if (limit.isRange()) {
                    list = list.subList(limit.begin(), limit.end());
                }
                if (limit.isLast()) {
                    list = list.subList(limit.last() < list.size() ? list.size() - limit.last() : 0, list.size());
                }
                if (limit.isTop()) {
                    list = list.subList(0, limit.top() < list.size() ? limit.top() : list.size());
                }
                beanMap.put(limtField.getName(), list);
            }
        }
    }
}
