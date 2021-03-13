package com.lambdaschool.countries.models;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country
    {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long countryid;

        private String name;
        private long population;
        private long landmasskm2;
        private long medianage;

        public Country()
        {
            //default constructor needed for JPA
        }

        public Country(
                String name,
                long population,
                long landmasskm2,
                long medianage)
        {
            this.name = name;
            this.population = population;
            this.landmasskm2 = landmasskm2;
            this.medianage = medianage;
        }

        public long getCountryid()
        {
            return countryid;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public long getPopulation()
        {
            return population;
        }

        public void setPopulation(long population)
        {
            this.population = population;
        }

        public long getLandmasskm2()
        {
            return landmasskm2;
        }

        public void setLandmasskm2(long landmasskm2)
        {
            this.landmasskm2 = landmasskm2;
        }

        public long getMedianage()
        {
            return medianage;
        }

        public void setMedianage(long medianage)
        {
            this.medianage = medianage;
        }

        @Override
        public String toString()
        {
            return "Country{" +
                    "countryid=" + countryid +
                    ", name='" + name + '\'' +
                    ", population=" + population +
                    ", landmasskm2=" + landmasskm2 +
                    ", medianage=" + medianage +
                    '}';
        }
    }
