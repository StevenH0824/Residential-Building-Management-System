package com.example.buildingmanagement.Controller;

import com.example.buildingmanagement.Services.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccessLogController {

  @Autowired
  private AccessLogService accessLogService;

}
