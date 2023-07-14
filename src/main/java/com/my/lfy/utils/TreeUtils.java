package com.my.lfy.utils;

import com.alibaba.fastjson.JSON;
import com.my.lfy.utils.model.Tree;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @FileName: TreeUtils
 * @Description: 树-工具类
 * @Author: lfy
 * @CreateDate: 2023/7/14 14:12
 * @Version: 1.0
 **/
public final class TreeUtils {


    private static final String ROOT_ID = "0";


    public static List<Tree> buildTree(List<Tree> treeList) {
        if (CollectionUtils.isEmpty(treeList)) {
            return new ArrayList<>();
        }

        // 筛选出parentId = "0"的根节点
        List<Tree> list = treeList.stream().filter(e -> e.getParentId().equals(ROOT_ID)).collect(Collectors.toList());

        // 根据parentId分组
        Map<String, List<Tree>> groupMap = treeList.stream().collect(Collectors.groupingBy(Tree::getParentId));

        // 递归子节点
        recursionFnTree(list, groupMap);
        return list;
    }

    private static void recursionFnTree(List<Tree> list, Map<String, List<Tree>> groupMap) {
        list.forEach(e -> {
            List<Tree> children = groupMap.get(e.getId());
            e.setChildren(children);
            if (CollectionUtils.isNotEmpty(children)) {
                recursionFnTree(children, groupMap);
            }
        });
    }

    public static void main(String[] args) {
        List<Tree> treeList = new ArrayList<>();
        treeList.add(Tree.builder().id("1")
                .label("第一级1")
                .parentId("0")
                .build());
        treeList.add(Tree.builder()
                .id("11")
                .label("第一子级1")
                .parentId("1")
                .build());
        treeList.add(Tree.builder()
                .id("111")
                .label("第一子级1的子级")
                .parentId("11")
                .build());

        treeList.add(Tree.builder().id("2")
                .label("第一级2")
                .parentId("0")
                .build());
        treeList.add(Tree.builder()
                .id("22")
                .label("第二子级2")
                .parentId("2")
                .build());
        System.out.println(JSON.toJSONString(buildTree(treeList)));
    }
}
