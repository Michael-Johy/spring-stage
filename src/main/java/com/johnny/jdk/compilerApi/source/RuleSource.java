package com.johnny.jdk.compilerApi.source;

import com.johnny.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * <p>
 * Author: yang_tao
 * Date  : 2017-07-24 16:03
 */
public class RuleSource implements RuleExecute {

    private static final Logger logger = LoggerFactory.getLogger(RuleSource.class);

    @Override
    public void execute(Person person) {
        person.setAge(11);
        person.setName("yang_tao");
        logger.info("Person:[" + person.getName() + "," + person.getAge() + "]");
    }
}
