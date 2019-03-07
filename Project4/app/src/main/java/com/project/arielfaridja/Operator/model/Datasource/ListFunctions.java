package com.project.arielfaridja.Operator.model.Datasource;

import com.project.arielfaridja.Operator.model.Entities.ActDes;
import com.project.arielfaridja.Operator.model.Entities.Address;
import com.project.arielfaridja.Operator.model.Entities.Date;
import com.project.arielfaridja.Operator.model.Entities.activity;
import com.project.arielfaridja.Operator.model.Entities.business;
import com.project.arielfaridja.Operator.model.Entities.user;

import java.util.ArrayList;

/**
 * Created by c plus on 03/01/2017.
 */

public class ListFunctions implements FunctionsInterface {

    int ActivitiesOriginalSize = 0;
    int BusinessOriginalSize = 0;

    @Override
    public int AddUser(user User) {
        Lists.Users.add(User);
        return User.getUserid();
    }
    ListFunctions()
    {
        AddUser(new user("a@a.com","1234"));
        AddBusiness(new business("Falij", new Address("Israel", "Karme Katif","Ha'gefen",7),"+972543081103","arielfrja@gmail.com", "www.falij.com"));
        AddActivity(new activity(ActDes.AIRLINE, "Israel", 500,new Date(15,10,2020), new Date(20,10,2020), "So wonderful!", 0));
        AddActivity(new activity(ActDes.AIRLINE, "Colombia", 200,new Date(17,1,2020), new Date(20,1,2020), "We like Colombia", 0));
        AddActivity(new activity(ActDes.AIRLINE, "france", 50,new Date(1,3,2023), new Date(14,3,2023), "Wanted to be in Cambodia and found myself in france!", 0));
    }
    @Override
    public int AddBusiness(business Business) {
        Lists.Business.add(Business);
        return Business.getID();
    }

    @Override
    public void AddActivity(activity Act) {
        Lists.Activities.add(Act);
    }

    @Override
    public boolean IsChangedSomthing() throws Exception {
        if (ActivitiesOriginalSize == this.GetAllActivities().size() && BusinessOriginalSize == this.GetAllBusiness().size()) {
            return false;
        }
        ActivitiesOriginalSize = this.GetAllActivities().size();
        BusinessOriginalSize = this.GetAllBusiness().size();
        return true;
    }

    @Override
    public ArrayList<user> GetAllUsers() throws Exception {
        return Lists.Users;
    }

    @Override
    public ArrayList<activity> GetAllActivities() throws Exception {
        return Lists.Activities;
    }

    @Override
    public ArrayList<business> GetAllBusiness() throws Exception {
        return Lists.Business;
    }

    /**
     * Created by Ariel Faridja on 25/12/2016.
     */

    public static class Lists {
        public static ArrayList<business> Business = new ArrayList<business>();
        public static ArrayList<activity> Activities = new ArrayList<activity>();
        ;
        public static ArrayList<user> Users = new ArrayList<user>();
        ;
    }
}
