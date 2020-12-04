package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {
    Admin selectByName(String username);

    Admin selctAllByName(String username);
}
