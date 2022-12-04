package com.qjx.sample._17._6_sepcial;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import lombok.experimental.FieldDefaults;

/**
 * @author: qinjiaxing
 * @Date: 2022/12/3 09:20
 * @Description:
 */
@With
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Model {
    String id;
    String name;
    String type;
}

