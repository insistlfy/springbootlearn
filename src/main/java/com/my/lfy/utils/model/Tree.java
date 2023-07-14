package com.my.lfy.utils.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.List;

/**
 * @FileName: Tree
 * @Description: 树对象
 * @Author: lfy
 * @CreateDate: 2023/7/14 14:14
 * @Version: 1.0
 **/
@Data
@Builder
public class Tree implements Serializable {

    /**
     * 节点id
     */
    private String id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 子节点
     */
    private List<Tree> children;

    @Tolerate
    public Tree() {
    }
}
