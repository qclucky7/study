package com.qingchen.study.jpa.master;

import com.qingchen.study.jpa.master.entity.Custom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName CustomRepository
 * @description:
 * @author: WangChen
 * @create: 2020-05-17 15:04
 **/
public interface CustomRepository extends JpaRepository<Custom, Long> {
}
