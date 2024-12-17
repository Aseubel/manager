package com.guyuan.manager.util;

import com.guyuan.manager.Entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HostHolder {
    private static final ThreadLocal<UserEntity> users = new ThreadLocal<>();

//    private static final ThreadLocal<List<String>> pages = new ThreadLocal<>();
//
//    private static final ThreadLocal<Integer> pageIndex = new ThreadLocal<>();
//
//    public static void setPageIndex(Integer pageIndex) {
//        HostHolder.pageIndex.set(pageIndex);
//    }
//
//    public static Integer getPageIndex() {
//        return pageIndex.get();
//    }
//
//    public static void setPages(List<String> pages) {
//        HostHolder.pages.set(pages);
//    }
//
//    public static List<String> getPages() {
//        return pages.get();
//    }

    public static void setUsers(UserEntity UserEntity) {
        users.set(UserEntity);
    }

    public static UserEntity getUsers() {
        return users.get();
    }

    public static void clear() {
//        pageIndex.remove();
//        pages.remove();
        users.remove();
    }
}
