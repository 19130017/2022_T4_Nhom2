package vn.weather.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.weather.model.Contact;

@Repository
public interface ContactService extends JpaRepository<Contact, Long> {
}
