package com.project.arielfaridja.Operator.model.Datasource;

import com.project.arielfaridja.Operator.model.Entities.activity;
import com.project.arielfaridja.Operator.model.Entities.business;
import com.project.arielfaridja.Operator.model.Entities.user;

import java.util.ArrayList;

/**
 * Created by c plus on 10/01/2017.
 */

public interface FunctionsInterface {


    int AddUser(user User);

    int AddBusiness(business Business);

    void AddActivity(activity Act);

    boolean IsChangedSomthing() throws Exception;

    ArrayList<user> GetAllUsers() throws Exception;

    ArrayList<activity> GetAllActivities() throws Exception;

    ArrayList<business> GetAllBusiness() throws Exception;
}
