package com.sopho.chat.FiltterPattern;

import java.util.List;

/**
 * author: kang4
 * Date: 2019/5/28
 * Description:
 */
public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}
