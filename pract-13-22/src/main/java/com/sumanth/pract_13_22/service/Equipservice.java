package com.sumanth.pract_13_22.service;

import com.opencsv.CSVReader;
import com.sumanth.pract_13_22.entity.Equipments;
import com.sumanth.pract_13_22.repository.Equipinterface;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
validate filetype if not csv throe error
open opencsv dependenccy
use thirdpary library and get whole data as array which is a row in db
upload to table
 */
@Service
public class Equipservice {
   @Autowired
   Equipinterface equipinterface;
    @Autowired
Multithreading multithreading;

    public void csvtodb(MultipartFile filename) throws Exception
    {
   String file= StringUtils.cleanPath(filename.getOriginalFilename());
   String fileExtension=StringUtils.getFilenameExtension(file);
        System.out.println(fileExtension);
        if(fileExtension.equals("csv")==false)
        {
            throw new Exception("please upload csv file");
        }


        try( Reader reader= new BufferedReader(new InputStreamReader(filename.getInputStream()));
             CSVReader csvReader = new CSVReader(reader);)
        {
//            Reader reader= new BufferedReader(new InputStreamReader(filename.getInputStream()));
//            CSVReader csvReader = new CSVReader(reader); // here we open the resourse but dint closed it so close it other wise it sits in the memory like that only

   //   System.out.println(Arrays.toString(csvReader.readNext()));

            String csvrow[]= csvReader.readNext();
            csvrow= csvReader.readNext();
            List<Equipments> eq= new ArrayList<>();

           System.out.println("insidecsvtodb  for for loop method"+ Thread.currentThread().getName());
            while (csvrow !=null)
            {
//               System.out.println(Arrays.toString(csvrow));
//                System.out.println( csvrow[0]);
                Equipments equipments= new Equipments();
              // equipments.setEqupid(Integer.parseInt(csvrow[0]));
                equipments.setCatid(Integer.parseInt(csvrow[1]));
                equipments.setEquipname(csvrow[2]);
                equipments.setEquipvalue(csvrow[3]);
                //logic to save each row     equipinterface.save(equipments); // use this with csvreader.readnext() at line 69
                // logic to save 1000 rows at a time to save time

                eq.add(equipments);
                if(eq.size() ==1000)
                {
                  multithreading.batchupload(new ArrayList<>(eq));// enable asyce in main for springboot to cretae threads
                //   equipinterface.saveAll(eq);
                    eq.clear();
                }


                csvrow=csvReader.readNext();
            }
            if(eq.size() >0)
            { multithreading.batchupload(new ArrayList<>(eq));
              //  equipinterface.saveAll(eq);// for uplading in db in batches
                eq.clear();
            }
        }
        catch(Exception e)
        {
throw new Exception("unable to psot to db"+e);
        }




//      csvReader.close();// noneed to do it manuaaly if we mention in try block the resourses will close automatically
//
//        reader.close();// pur responsiblity to close the resourse otherwise ram fills and systema slows
/*
Its not good practice beacause jpa fetches each row and connects to db and saves it and close the connection to db and comes again feaches
other row and repa=etas process so to avoid that we upload it in batches
 */
    }


}
