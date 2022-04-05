package com.notification.producer.service;

import com.notification.producer.model.Notification;

public interface NotificationService {

    void send(Notification notification);
}
