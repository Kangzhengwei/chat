package com.sopho.chat.FiltterPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * author: kang4
 * Date: 2019/5/28
 * Description:
 */
public class CriteriaFemale implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<>();
        for (Person person : persons) {
            if (person.getGender().equalsIgnoreCase("女")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
