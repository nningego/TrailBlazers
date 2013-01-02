package com.thoughtworks.twu.dao.impl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-persistance-config.xml")
@TestExecutionListeners({TransactionalTestExecutionListener.class})
@TransactionConfiguration(transactionManager = "transactionManager")
public abstract class DaoTest extends AbstractTransactionalJUnit4SpringContextTests {

}
