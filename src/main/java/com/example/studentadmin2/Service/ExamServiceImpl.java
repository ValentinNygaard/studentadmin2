package com.example.studentadmin2.Service;

import com.example.studentadmin2.Model.Exam;
import com.example.studentadmin2.Repository.ExamRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements IService<Exam> {

    @Autowired
    ExamRepoImpl repoImpl;

    @Override
    public List<Exam> findAll() {
        return repoImpl.findAll();
    }

    @Override
    public Exam findById(int id) {
        return repoImpl.findById(id);
    }

    @Override
    public Exam create(Exam exam) {
        return repoImpl.create(exam);
    }

    @Override
    public Exam update(Exam exam) {
        return repoImpl.update(exam);
    }

    @Override
    public boolean delete(int id) { return repoImpl.delete(id); }

}