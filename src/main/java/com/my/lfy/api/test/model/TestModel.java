package com.my.lfy.api.test.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * TestModel
 *
 * @author lfy
 * @date 2020/7/17
 **/
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestModel implements Serializable {

    private static final long serialVersionUID = -7856574730544132842L;

    private List<String> code;
}
