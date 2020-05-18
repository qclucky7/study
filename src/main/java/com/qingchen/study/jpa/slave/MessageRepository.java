package com.qingchen.study.jpa.slave;

import com.qingchen.study.jpa.slave.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @InterfaceName MessageRepository
 * @description:
 * @author: WangChen
 * @create: 2020-05-17 16:35
 **/
public interface MessageRepository extends JpaRepository<Message, Long> {
}
