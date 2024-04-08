package com.vivalahm.shop.repository;

import com.vivalahm.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Member findByUserName(String userName);

}
