package com.sopho.chat.FiltterPattern;

import java.util.List;

/**
 * author: kang4
 * Date: 2019/5/28
 * Description:
 */
public class OrCriteria implements Criteria {
    private Criteria otherCritera;
    private Criteria criteria;

    public OrCriteria(Criteria criteria, Criteria otherCritera) {
        this.criteria = criteria;
        this.otherCritera = otherCritera;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteraitems = criteria.meetCriteria(persons);
        List<Person> otherCriteriaItems = otherCritera.meetCriteria(persons);
        for (Person person : otherCriteriaItems) {
            if (!firstCriteraitems.contains(person)) {
                firstCriteraitems.add(person);
            }
        }
        return firstCriteraitems;
    }
}
