package com.example.attendanceservice.services;


import com.example.attendanceservice.entities.Attendance;
import com.example.attendanceservice.entities.User;
import com.example.attendanceservice.entities.UserAttendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Service
public class QueryService {

    @Autowired
    EntityManagerFactory emf;

    public List<UserAttendance> JPQLQuery()
    {
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin( );

        Query query = em.createQuery("Select" +" u.userName, a.report"
                +" from attendance a " +
                "inner join users u on u.id=a.user.id where a.date= :date");

        query.setParameter("date",java.time.LocalDate.now() + "");

        @SuppressWarnings("unchecked")
        List<UserAttendance> list =(List<UserAttendance>)query.getResultList();
        em.close();

        return list;

    }

    public List<UserAttendance> JPQLQueryTwo(int month)
    {
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin( );

        Query query = em.createQuery("Select" +" u.userName, a.report"
                +" from attendance a " +
                "inner join users u on u.id=a.user.id  WHERE MONTH(date)= :month");

        query.setParameter("month",month);

        @SuppressWarnings("unchecked")
        List<UserAttendance> list =(List<UserAttendance>)query.getResultList();
        em.close();

        return list;

    }



}
