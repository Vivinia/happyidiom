package cn.edu.bztc.happyidiom.test;

import java.util.List;

import cn.edu.bztc.happyidiom.dao.AnimalDao;
import cn.edu.bztc.happyidiom.entity.Animal;
import android.test.AndroidTestCase;

public class AnimalDaoTest extends AndroidTestCase{
	public void testGetAllAnimals(){
		AnimalDao animalDao=AnimalDao.getInstance(getContext());
		List<Animal> animals=animalDao.getAllAnimals();
		System.out.println(animals.size());
		for(Animal animal:animals){
			System.out.println(animal.getName());
		}
	}
}
