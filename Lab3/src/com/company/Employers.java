package com.company;

import java.util.Comparator;
import java.util.Date;

public class Employers {
    private String lastName;
    private String position;
    private Date birthDate;
    private double salary;

   @Override
    public int hashCode() {
        return lastName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employers other = (Employers) obj;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }

    public static class AgeComparator implements java.util.Comparator<Employers> {
        public int compare(Employers e1, Employers e2) {
            return e1.birthDate.compareTo(e2.birthDate);
        }
    }

    public static class SalaryComparator implements java.util.Comparator<Employers> {
        public int compare(Employers e1, Employers e2) {
            return  Double.compare(e1.salary,e2.salary);
        }
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public double getSalary() {
        return salary;
    }

    public void Output() {
        System.out.format("| %20s | %10s | %20s | %10.2f |\n", lastName, position,
                birthDate.getDate() + "-" + (birthDate.getMonth()+1) + "-" + (birthDate.getYear()+1900), salary);
    }
}
