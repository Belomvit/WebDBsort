package com.mycompany.client;

import com.google.gson.Gson;
import com.mycompany.ser.Gpu;
import com.mycompany.ser.GpuClock;
import com.mycompany.ser.Memory;
import com.mycompany.ser.MemoryClock;
import com.mycompany.ser.Price;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import njc.gpuClockNewJerseyClient;
import njc.gpuNewJerseyClient;
import njc.memClockNewJerseyClient;
import njc.memNewJerseyClient;
import njc.priceNewJerseyClient;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    @SuppressWarnings("null")
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        HorizontalLayout hlayout = new HorizontalLayout();
        HorizontalLayout hlayout2 = new HorizontalLayout();
        HorizontalLayout hlayout3 = new HorizontalLayout();
        HorizontalLayout hlayout4 = new HorizontalLayout();
        VerticalLayout layout2 = new VerticalLayout();
        VerticalLayout layout3 = new VerticalLayout();
        VerticalLayout layout4 = new VerticalLayout();

        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");


        
        Grid<Gpu> gridGpu = new Grid<>();
        gridGpu.addColumn(Gpu::getIdgpu).setCaption("Id");
        gridGpu.addColumn(Gpu::getName).setCaption("Name");
        gridGpu.addColumn(Gpu::getMemory).setCaption("Memory");
        gridGpu.addColumn(Gpu::getGpuclock).setCaption("Gpu Clock");
        gridGpu.addColumn(Gpu::getMemoryclock).setCaption("Memory Clock");
        gridGpu.addColumn(Gpu::getPrice).setCaption("Price");
        
        //gridGpu.addColumn(Gpu::getScore).setCaption("Score");//
        
        gridGpu.setWidth("900px"); gridGpu.setHeight("700px");
        
        Grid<Memory> gridMem = new Grid<>();
        gridMem.addColumn(Memory::getIdMemory).setCaption("Id");
        gridMem.addColumn(Memory::getMemory).setCaption("memory");
        gridMem.setWidth("900px"); gridMem.setHeight("700px");
        
        Grid<GpuClock> gridGpuClock = new Grid<>();
        gridGpuClock.addColumn(GpuClock::getIdGpuClock).setCaption("Id");
        gridGpuClock.addColumn(GpuClock::getGpuClock).setCaption("GpuClock");
        gridGpuClock.setWidth("900px"); gridGpuClock.setHeight("700px");
        
        Grid<MemoryClock> gridMemoryClock = new Grid<>();
        gridMemoryClock.addColumn(MemoryClock::getIdMemoryClock).setCaption("Id");
        gridMemoryClock.addColumn(MemoryClock::getMemoryClock).setCaption("MemoryClock");
        gridMemoryClock.setWidth("900px"); gridMemoryClock.setHeight("700px");
        
        Grid<Price> gridPrice = new Grid<>();
        gridPrice.addColumn(Price::getIdPrice).setCaption("Id");
        gridPrice.addColumn(Price::getPrice).setCaption("Price");
        gridPrice.setWidth("900px"); gridPrice.setHeight("700px");
        
        Button buttonSort = new Button("Sort databaze");
        buttonSort.addClickListener((ClickEvent e) -> {
            gpuNewJerseyClient restClientGpu = new gpuNewJerseyClient();
            GenericType<List<Gpu>> gType = new GenericType<List<Gpu>>(){};
            List<Gpu> gpuList = restClientGpu.findAll_JSON(gType);
           
            List<Memory> memList = new ArrayList<Memory>();
            List<MemoryClock> memClockList = new ArrayList<MemoryClock>();
            List<GpuClock> GpuClockList = new ArrayList<GpuClock>();
            List<Price> PriceList = new ArrayList<Price>();
            
            long start = System.currentTimeMillis();

            Integer minMem=0, maxMem=0, minMemCl=0, maxMemCl=0, minPrice=0, maxPrice=0, minGpuCl=0, maxGpuCl=0;
            for (int i = 0; i < gpuList.size(); i++) {
                if(i==0){
                   minMem=gpuList.get(i).getMemory(); maxMem=gpuList.get(i).getMemory();
                   minMemCl=gpuList.get(i).getMemoryclock(); maxMemCl=gpuList.get(i).getMemoryclock();
                   minPrice=gpuList.get(i).getPrice(); maxPrice=gpuList.get(i).getPrice();
                   minGpuCl=gpuList.get(i).getGpuclock(); maxGpuCl=gpuList.get(i).getGpuclock();
                   continue;
                }
                
                if(gpuList.get(i).getMemory() < minMem ){ minMem = gpuList.get(i).getMemory();}
                if(gpuList.get(i).getMemory() > maxMem ){ maxMem = gpuList.get(i).getMemory();}
                if(gpuList.get(i).getMemoryclock() < minMemCl ){ minMemCl = gpuList.get(i).getMemoryclock();}
                if(gpuList.get(i).getMemoryclock() > maxMemCl ){ maxMemCl = gpuList.get(i).getMemoryclock();}
                if(gpuList.get(i).getPrice() < minPrice ){ minPrice = gpuList.get(i).getPrice();}
                if(gpuList.get(i).getPrice() > maxPrice ){ maxPrice = gpuList.get(i).getPrice();}
                if(gpuList.get(i).getGpuclock() < minGpuCl ){ minGpuCl = gpuList.get(i).getGpuclock();}
                if(gpuList.get(i).getGpuclock() > maxGpuCl ){ maxGpuCl = gpuList.get(i).getGpuclock();}
            }
            
            for (int i = 0; i < gpuList.size(); i++) {
                Double x = 1000*normalize(gpuList.get(i).getMemory(), minMem, maxMem) ;
                Integer MemVal = x.intValue()+1;
                Memory tmp = new Memory();
                tmp.setIdMemory(gpuList.get(i).getIdgpu()); tmp.setMemory(MemVal);
                memList.add(tmp);
                
                x = 1000*normalize(gpuList.get(i).getGpuclock(), minGpuCl, maxGpuCl) ;
                MemVal = x.intValue()+1;
                GpuClock tmp1 = new GpuClock();
                tmp1.setIdGpuClock(gpuList.get(i).getIdgpu()); tmp1.setGpuClock(MemVal);
                GpuClockList.add(tmp1);
                
                x = 1000*normalize(gpuList.get(i).getMemoryclock(), minMemCl, maxMemCl) ;
                MemVal = x.intValue()+1;
                MemoryClock tmp2 = new MemoryClock();
                tmp2.setIdMemoryClock(gpuList.get(i).getIdgpu()); tmp2.setMemoryClock(MemVal);
                memClockList.add(tmp2);
                
                x = 1000*normalize(gpuList.get(i).getPrice(), minPrice, maxPrice) ;
                MemVal = x.intValue()+1;
                MemVal = 1002 - MemVal;
                Price tmp3 = new Price();
                tmp3.setIdPrice(gpuList.get(i).getIdgpu()); tmp3.setPrice(MemVal);
                PriceList.add(tmp3);
                
            }
            
            Collections.sort(memList,new MemoryComare());
            Collections.sort(memClockList,new MemoryClockComare());
            Collections.sort(GpuClockList,new GpuClockComare());
            Collections.sort(PriceList,new PriceComare());
            
            for (int i = 0; i < memList.size(); i++) {
                memNewJerseyClient MemRestClient = new memNewJerseyClient();
                MemRestClient.create_JSON(memList.get(i));
            }
            
            for (int i = 0; i < memClockList.size(); i++) {
                memClockNewJerseyClient MemClockRestClient = new memClockNewJerseyClient();
                MemClockRestClient.create_JSON(memClockList.get(i));
            }
            
            for (int i = 0; i < GpuClockList.size(); i++) {
                gpuClockNewJerseyClient GpuClockRestClient = new gpuClockNewJerseyClient();
                GpuClockRestClient.create_JSON(GpuClockList.get(i));
            }
            
            for (int i = 0; i < PriceList.size(); i++) {
                priceNewJerseyClient PriceRestClient = new priceNewJerseyClient();
                PriceRestClient.create_JSON(PriceList.get(i));
            }
            
            long time = System.currentTimeMillis() - start;
            Label sortedDB = new Label("Databaze sorted in " + time + " ms" );
            layout2.removeComponent(sortedDB);
            layout2.addComponent(sortedDB);
        });
        
        
        
        
        
        final TextField KField = new TextField(); KField.setCaption("Type K number of objects:");
        CheckBox checkboxGpuClock = new CheckBox("Gpu Clock");
        CheckBox checkboxMemory = new CheckBox("Memory");
        CheckBox checkboxMemoryClock = new CheckBox("Memory Clock");
        CheckBox checkboxPrice = new CheckBox("Price");
          
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        Button StartFagin = new Button("Start Fagin alg.");
        StartFagin.addClickListener((Button.ClickEvent event) -> {
            if(KField.getValue().isEmpty()){
                Notification.show("Fill K box", Type.ERROR_MESSAGE); 
                return;
            }
            
            List<Gpu> tableF = new ArrayList<Gpu>();
            List<Gpu> tableForOutFagin = new ArrayList<Gpu>();
            
            Boolean done = false;
            
            gpuClockNewJerseyClient restClientGC = new gpuClockNewJerseyClient(); GenericType<List<GpuClock>> gTypeGC = new GenericType<List<GpuClock>>(){};
            memNewJerseyClient restClientM = new memNewJerseyClient(); GenericType<List<Memory>> gTypeM = new GenericType<List<Memory>>(){};
            memClockNewJerseyClient restClientMC = new memClockNewJerseyClient(); GenericType<List<MemoryClock>> gTypeMC = new GenericType<List<MemoryClock>>(){};            
            priceNewJerseyClient restClientP = new priceNewJerseyClient(); GenericType<List<Price>> gTypeP = new GenericType<List<Price>>(){};
            
            long start = System.currentTimeMillis();
            
            if(checkboxGpuClock.getValue()){
                List<GpuClock> ListGpuClock = restClientGC.getTopK(gTypeGC, KField.getValue());
                for (int i = 0; i < ListGpuClock.size(); i++) {
                    done = false;
                    for (int j = 0; j < tableF.size(); j++) {
                        if(tableF.get(j).getIdgpu() == ListGpuClock.get(i).getIdGpuClock()){
                            tableF.get(j).setGpuclock(ListGpuClock.get(i).getGpuClock());
                            done = true;
                            break;
                        }
                    }
                    if(done == false){
                        tableF.add(new Gpu(ListGpuClock.get(i).getIdGpuClock()));
                        tableF.get(tableF.size() - 1).setGpuclock(ListGpuClock.get(i).getGpuClock());
                    }
                }
            }
           
            if(checkboxMemory.getValue()){
                List<Memory> ListMemory = restClientM.getTopK(gTypeM, KField.getValue());
                for (int i = 0; i < ListMemory.size(); i++) {
                    done = false;
                    for (int j = 0; j < tableF.size(); j++) {
                        if(tableF.get(j).getIdgpu() == ListMemory.get(i).getIdMemory()){
                            tableF.get(j).setMemory(ListMemory.get(i).getMemory());
                            done = true;
                            break;
                        }
                    }
                    if(done == false){
                        tableF.add(new Gpu(ListMemory.get(i).getIdMemory()));
                        tableF.get(tableF.size() - 1).setMemory(ListMemory.get(i).getMemory());
                    }
                }
            }
            
            if(checkboxMemoryClock.getValue()){
                List<MemoryClock> ListMemoryClocks = restClientMC.getTopK(gTypeMC, KField.getValue());
                for (int i = 0; i < ListMemoryClocks.size(); i++) {
                    done = false;
                    for (int j = 0; j < tableF.size(); j++) {
                        if(tableF.get(j).getIdgpu() == ListMemoryClocks.get(i).getIdMemoryClock()){
                            tableF.get(j).setMemoryclock(ListMemoryClocks.get(i).getMemoryClock());
                            done = true;
                            break;
                        }
                    }
                    if(done == false){
                        tableF.add(new Gpu(ListMemoryClocks.get(i).getIdMemoryClock()));
                        tableF.get(tableF.size() - 1).setMemoryclock(ListMemoryClocks.get(i).getMemoryClock());
                    }
                }
            }
            
            if(checkboxPrice.getValue()){
                List<Price> ListPrice = restClientP.getTopK(gTypeP, KField.getValue());
                for (int i = 0; i < ListPrice.size(); i++) {
                    done = false;
                    for (int j = 0; j < tableF.size(); j++) {
                        if(tableF.get(j).getIdgpu() == ListPrice.get(i).getIdPrice()){
                            tableF.get(j).setPrice(ListPrice.get(i).getPrice());
                            done = true;
                            break;
                        }
                    }
                    if(done == false){
                        tableF.add(new Gpu(ListPrice.get(i).getIdPrice()));
                        tableF.get(tableF.size() - 1).setPrice(ListPrice.get(i).getPrice());
                    }
                }
            }

            //long start2 = System.currentTimeMillis();
            
            for (int i = 0; i < tableF.size(); i++) {
                if(checkboxGpuClock.getValue()){
                    if(tableF.get(i).getGpuclock() == 0){
                        String str = restClientGC.find_JSON(gTypeGC, tableF.get(i).getIdgpu().toString());
                        Gson g = new Gson();
                        GpuClock tmp = g.fromJson(str, GpuClock.class);
                        tableF.get(i).setGpuclock(tmp.getGpuClock());
                    }
                }
                if(checkboxMemoryClock.getValue()){
                    if(tableF.get(i).getMemoryclock() == 0){
                        String str = restClientMC.find_JSON(gTypeMC, tableF.get(i).getIdgpu().toString());
                        Gson g = new Gson();
                        MemoryClock tmp = g.fromJson(str, MemoryClock.class);
                        tableF.get(i).setMemoryclock(tmp.getMemoryClock());
                    }
                }
                if(checkboxMemory.getValue()){
                    if(tableF.get(i).getMemory() == 0){
                        String str = restClientM.find_JSON(gTypeM, tableF.get(i).getIdgpu().toString());
                        Gson g = new Gson();
                        Memory tmp = g.fromJson(str, Memory.class);
                        tableF.get(i).setMemory(tmp.getMemory());
                    }
                }     
                if(checkboxPrice.getValue()){
                    if(tableF.get(i).getPrice() == 0){
                        String str = restClientP.find_JSON(gTypeP, tableF.get(i).getIdgpu().toString());
                        Gson g = new Gson();
                        Price tmp = g.fromJson(str, Price.class);
                        tableF.get(i).setPrice(tmp.getPrice());
                    }
                }              
            }
            //long time2 = System.currentTimeMillis() - start2;
            
            for (int i = 0; i < tableF.size(); i++) {
                Integer count = 0;
                if(checkboxGpuClock.getValue()){
                    if(tableF.get(i).getScore() < tableF.get(i).getGpuclock() || tableF.get(i).getScore() == 0){
                        tableF.get(i).setScore(tableF.get(i).getGpuclock());
                    }
                }
                if(checkboxMemoryClock.getValue()){
                    if(tableF.get(i).getScore() < tableF.get(i).getMemoryclock() || tableF.get(i).getScore() == 0){
                        tableF.get(i).setScore(tableF.get(i).getMemoryclock());
                    }
                }
                if(checkboxMemory.getValue()){
                    if(tableF.get(i).getScore() < tableF.get(i).getMemory()|| tableF.get(i).getScore() == 0){
                        tableF.get(i).setScore(tableF.get(i).getMemory());
                    }
                }
                if(checkboxPrice.getValue()){
                    if(tableF.get(i).getScore() < tableF.get(i).getPrice()|| tableF.get(i).getScore() == 0){
                        tableF.get(i).setScore(tableF.get(i).getPrice());
                    }
                }
            }

            gpuNewJerseyClient restClientGPU = new gpuNewJerseyClient();
            GenericType<List<Gpu>> gTypeGPU = new GenericType<List<Gpu>>(){};
            
            List<Gpu> listFaginSorted = new ArrayList<Gpu>();
            for (int i = 0; i < Integer.parseInt(KField.getValue()); i++) {
                Integer maxScore = -1, maxID =0, maxI= 0;
                for (int j = 0; j < tableF.size(); j++) {
                    if(i == 0 ) tableForOutFagin.add(tableF.get(j));
                    if(tableF.get(j).getScore() > maxScore) {
                        maxID = tableF.get(j).getIdgpu();
                        maxI = j;
                        maxScore = tableF.get(j).getScore();
                    }
                }
                
                String str = restClientGPU.find_JSON(gTypeGPU, maxID.toString());
                Gson g = new Gson();
                Gpu tmp = g.fromJson(str, Gpu.class);
                tmp.setScore(maxScore);
                listFaginSorted.add(tmp);
                tableF.remove(tableF.get(maxI));  
            }
            
            long time = System.currentTimeMillis() - start;
            
            //Fagin table ///////////////////////////////////
//            for (int i = 0; i < listFaginSorted.size(); i++) {
//                tableF.add(listFaginSorted.get(i));
//            }
            //Collections.sort(tableF, new ScoreComare());
            
            Grid<Gpu> gridFagin = new Grid<>();
            gridFagin.addColumn(Gpu::getIdgpu).setCaption("Id");
            if(checkboxGpuClock.getValue()){
                gridFagin.addColumn(Gpu::getGpuclock).setCaption("Gpu Clock");
            }
            if(checkboxMemory.getValue()){
                gridFagin.addColumn(Gpu::getMemory).setCaption("Memory");
            }
            
            if(checkboxMemoryClock.getValue()){
                gridFagin.addColumn(Gpu::getMemoryclock).setCaption("Memory Clock");
            }
            if(checkboxPrice.getValue()){
               gridFagin.addColumn(Gpu::getPrice).setCaption("Price");                
            }
            gridFagin.addColumn(Gpu::getScore).setCaption("Score");
            gridFagin.setWidth("900px"); gridFagin.setHeight("800px"); 
            

            Label TopkLabel = new Label("Sorted by chosen attributes table (Fagin alg.). Algorithm take: " +  + time + " ms" /* + time2 */);
            gridGpu.setItems(listFaginSorted);
            layout4.removeAllComponents();
            layout4.addComponent(TopkLabel);
            layout4.addComponent(gridGpu);
            
            Label FaginTime = new Label("Fagin table. ");
            gridFagin.setItems(tableForOutFagin);
            layout4.addComponent(FaginTime);
            layout4.addComponent(gridFagin);
        });
        
        
        
         /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        Button StartSequential = new Button("Start sequential");
        StartSequential.addClickListener((Button.ClickEvent event) -> {
            if(KField.getValue().isEmpty()){
                Notification.show("Fill K box", Type.ERROR_MESSAGE); 
                return;
            }
            gpuNewJerseyClient restClient = new gpuNewJerseyClient();
            GenericType<List<Gpu>> gType = new GenericType<List<Gpu>>(){};
            List<Gpu> ow = restClient.findAll_JSON(gType);
            List<Gpu> gpuListTopK = new ArrayList<Gpu>();
            
            long start = System.currentTimeMillis();
            
            Integer minMem=0, maxMem=0, minMemCl=0, maxMemCl=0, minPrice=0, maxPrice=0, minGpuCl=0, maxGpuCl=0;
            for (int i = 0; i < ow.size(); i++) {
                if(i==0){
                   minMem=ow.get(i).getMemory(); maxMem=ow.get(i).getMemory();
                   minMemCl=ow.get(i).getMemoryclock(); maxMemCl=ow.get(i).getMemoryclock();
                   minPrice=ow.get(i).getPrice(); maxPrice=ow.get(i).getPrice();
                   minGpuCl=ow.get(i).getGpuclock(); maxGpuCl=ow.get(i).getGpuclock();
                   continue;
                }
                
                if(ow.get(i).getMemory() < minMem ){ minMem = ow.get(i).getMemory();}
                if(ow.get(i).getMemory() > maxMem ){ maxMem = ow.get(i).getMemory();}
                if(ow.get(i).getMemoryclock() < minMemCl ){ minMemCl = ow.get(i).getMemoryclock();}
                if(ow.get(i).getMemoryclock() > maxMemCl ){ maxMemCl = ow.get(i).getMemoryclock();}
                if(ow.get(i).getPrice() < minPrice ){ minPrice = ow.get(i).getPrice();}
                if(ow.get(i).getPrice() > maxPrice ){ maxPrice = ow.get(i).getPrice();}
                if(ow.get(i).getGpuclock() < minGpuCl ){ minGpuCl = ow.get(i).getGpuclock();}
                if(ow.get(i).getGpuclock() > maxGpuCl ){ maxGpuCl = ow.get(i).getGpuclock();}
            }
            
            
            for (int i = 0; i < ow.size(); i++) {
                Integer count=0;
                if(checkboxGpuClock.getValue()){
                    Double x = 1000*normalize(ow.get(i).getGpuclock(), minGpuCl, maxGpuCl) ;
                    Integer Val = x.intValue()+1;
                    
                    if(ow.get(i).getScore() < Val|| ow.get(i).getScore() == 0){
                        ow.get(i).setScore(Val);
                    }
                }
                if(checkboxMemory.getValue()){
                    Double x = 1000*normalize(ow.get(i).getMemory(), minMem, maxMem) ;
                    Integer Val = x.intValue()+1;
                    if(ow.get(i).getScore() < Val|| ow.get(i).getScore() == 0){
                        ow.get(i).setScore(Val);
                    }
                }
                if(checkboxMemoryClock.getValue()){
                    Double x = 1000*normalize(ow.get(i).getMemoryclock(), minMemCl, maxMemCl) ;
                    Integer Val = x.intValue()+1;
                    if(ow.get(i).getScore() < Val|| ow.get(i).getScore() == 0){
                        ow.get(i).setScore(Val);
                    }
                }
                if(checkboxPrice.getValue()){
                    Double x = 1000*normalize(ow.get(i).getPrice(), minPrice, maxPrice) ;
                    Integer Val = x.intValue()+1;
                    Val = 1002 - Val;
                    if(ow.get(i).getScore() < Val|| ow.get(i).getScore() == 0){
                        ow.get(i).setScore(Val);
                    }
                }
            }
            
            for (int i = 0; i < Integer.parseInt(KField.getValue()); i++) {
                Integer maxScore = -1, maxID =0;
                for (int j = 0; j < ow.size(); j++) {
                    if(ow.get(j).getScore() > maxScore) {
                        maxID = j;
                        maxScore = ow.get(j).getScore();
                    }
                }
                gpuListTopK.add(ow.get(maxID));
                ow.remove(ow.get(maxID));   
            }

            long time = System.currentTimeMillis() - start;
            
            Label TopkLabel = new Label("Sorted by chosen attributes table (Seq.). Algorithm take: " +  + time + " ms"  );
            gridGpu.setItems(gpuListTopK);
            layout4.removeAllComponents();
            layout4.addComponent(TopkLabel);
            layout4.addComponent(gridGpu);

        });        

         /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        Button MemoryButtom = new Button("Show sorted memory");
        MemoryButtom.addClickListener((Button.ClickEvent event) -> {
            refreshGridMemory(gridMem);
            layout4.removeAllComponents();
            layout4.addComponents(gridMem);  
        });
        
        Button GpuClockButtom = new Button("Show sorted gpu clock");
        GpuClockButtom.addClickListener((Button.ClickEvent event) -> {
            refreshGridGpuClock(gridGpuClock);
            layout4.removeAllComponents();
            layout4.addComponents(gridGpuClock);  
        });
        
        Button MemoryClockButtom = new Button("Show sorted memory clock");
        MemoryClockButtom.addClickListener((Button.ClickEvent event) -> {
            refreshGridMemClock(gridMemoryClock);
            layout4.removeAllComponents();
            layout4.addComponents(gridMemoryClock);  
        });
        
        Button PriceButtom = new Button("Show sorted price");
        PriceButtom.addClickListener((Button.ClickEvent event) -> {
            refreshGridPrice(gridPrice);
            layout4.removeAllComponents();
            layout4.addComponents(gridPrice);
        });        

        
        Button GpuButtom = new Button("Show all GPU");
        GpuButtom.addClickListener((Button.ClickEvent event) -> {
            refreshGridGpu(gridGpu);
            layout4.removeAllComponents();
            layout4.addComponents(gridGpu);
        });
        
        Button DelButtom = new Button("Delete sorted lists from DB");
        DelButtom.addClickListener((Button.ClickEvent event) -> {
            gpuClockNewJerseyClient restClientGC = new gpuClockNewJerseyClient();
            memNewJerseyClient restClientM = new memNewJerseyClient(); 
            memClockNewJerseyClient restClientMC = new memClockNewJerseyClient();             
            priceNewJerseyClient restClientP = new priceNewJerseyClient(); 
            
            String sizeS = restClientGC.countREST();
            Integer size = Integer.parseInt(sizeS);
            for (int i = 1; i <= size; i++) {
                restClientGC.remove(String.valueOf(i));
            }
            sizeS = restClientM.countREST();
            size = Integer.parseInt(sizeS);
            for (int i = 1; i <= size; i++) {
                restClientM.remove(String.valueOf(i));
            }
            sizeS = restClientMC.countREST();
            size = Integer.parseInt(sizeS);
            for (int i = 1; i <= size; i++) {
                restClientMC.remove(String.valueOf(i));
            }
            sizeS = restClientP.countREST();
            size = Integer.parseInt(sizeS);
            for (int i = 1; i <= size; i++) {
                restClientP.remove(String.valueOf(i));
            }
            Notification.show("Done", Type.HUMANIZED_MESSAGE); 
        });
       
        Label labalSetSort = new Label("Set soring parameters:");
        Label labelDBsort = new Label("Databaze sorting:"   );
        

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        layout2.addComponents(GpuButtom, GpuClockButtom, MemoryButtom, MemoryClockButtom, PriceButtom, labelDBsort, buttonSort, DelButtom);
        layout3.addComponents(labalSetSort, checkboxMemory,checkboxMemoryClock,checkboxGpuClock,checkboxPrice, KField, StartFagin  ,StartSequential);
        hlayout.addComponents(layout2, layout3, layout4);
        layout.addComponent(hlayout);
        layout.addComponent(hlayout2);
        layout.addComponent(hlayout3);
        ////////
        setContent(layout);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }


    
    
    
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    

    
    
    public Grid<Gpu> refreshGridGpu (Grid<Gpu> grid){
        gpuNewJerseyClient restClient = new gpuNewJerseyClient();
        GenericType<List<Gpu>> gType = new GenericType<List<Gpu>>(){};
        List<Gpu> ow = restClient.findAll_JSON(gType);
        grid.setItems(ow);
        return grid;
    }
    
    
    public Grid<Memory> refreshGridMemory (Grid<Memory> grid){
        memNewJerseyClient restClient = new memNewJerseyClient();
        GenericType<List<Memory>> gType = new GenericType<List<Memory>>(){};
        List<Memory> ow = restClient.findAll_JSON(gType);
        grid.setItems(ow);
        return grid;
    }
    
    public Grid<GpuClock> refreshGridGpuClock (Grid<GpuClock> grid){
        gpuClockNewJerseyClient restClient = new gpuClockNewJerseyClient();
        GenericType<List<GpuClock>> gType = new GenericType<List<GpuClock>>(){};
        List<GpuClock> ow = restClient.findAll_JSON(gType);
        grid.setItems(ow);
        return grid;
    }
    
    public Grid<MemoryClock> refreshGridMemClock (Grid<MemoryClock> grid){
        memClockNewJerseyClient restClient = new memClockNewJerseyClient();
        GenericType<List<MemoryClock>> gType = new GenericType<List<MemoryClock>>(){};
        List<MemoryClock> ow = restClient.findAll_JSON(gType);
        grid.setItems(ow);
        return grid;
    }
    
    public Grid<Price> refreshGridPrice (Grid<Price> grid){
        priceNewJerseyClient restClient = new priceNewJerseyClient();
        GenericType<List<Price>> gType = new GenericType<List<Price>>(){};
        List<Price> ow = restClient.findAll_JSON(gType);
        grid.setItems(ow);
        return grid;
    }

    
    public class MemoryComare implements Comparator<Memory> {
        @Override
        public int compare(Memory o1, Memory o2) {
            return o2.getMemory().compareTo(o1.getMemory());
        }
    }
    
    public class GpuClockComare implements Comparator<GpuClock> {
        @Override
        public int compare(GpuClock o1, GpuClock o2) {
            return o2.getGpuClock().compareTo(o1.getGpuClock());
        }
    }
    
    public class MemoryClockComare implements Comparator<MemoryClock> {
        @Override
        public int compare(MemoryClock o1, MemoryClock o2) {
            return o2.getMemoryClock().compareTo(o1.getMemoryClock());
        }
    }
    
    public class PriceComare implements Comparator<Price> {
        @Override
        public int compare(Price o1, Price o2) {
            return o2.getPrice().compareTo(o1.getPrice());
        }
    }
    
    public class ScoreComare implements Comparator<Gpu> {
        @Override
        public int compare(Gpu o1, Gpu o2) {
            return o2.getScore().compareTo(o1.getScore());
        }
    }

    
    public double normalize (int my, int min, int max){
        double Xnew;
        Xnew = ((double)my - (double)min)/((double)max- (double)min);
        return Xnew;
    }
    
}
