package de.telran._2023_08_31.practice.restaurant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Kitchen {
    private final ExecutorService chefThreadPool; // наши повара

    public Kitchen(int chefCount) {
        chefThreadPool = Executors.newFixedThreadPool(chefCount); // Используем ThreadPool для ограничения количества одновременно работающих поваров.
    }

    public void cookDish(Dish dish) {
        chefThreadPool.execute(() -> {
            System.out.println("cooking: " + dish.getName() + " with thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(dish.getPreparationTime());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(dish.getName() + "The process was interrupted");
            }
            dish.setStatusDish(Status.READY);
            System.out.println(dish.getName() + " is ready!");
            synchronized (this) {
                this.notifyAll();
            }
        });
    }

   public void cookOrder(Order order) {
        order.getDishes().forEach((dish -> {
            chefThreadPool.execute(() -> {
                System.out.println("cooking: " + dish.getName() + " with thread: " + Thread.currentThread().getName());
                try {
                    dish.setStatusDish(Status.COOKING);
                    Thread.sleep(dish.getPreparationTime());
                    dish.setStatusDish(Status.READY);
                    synchronized (order) {
                        try{
                            System.out.println(dish.getName() + " заказа №"+order.getId()+" выполнен! ---" + System.currentTimeMillis());
                            System.out.println(dish.getName() + " ждем вызов метода notify: " + System.currentTimeMillis());
//                            Thread.sleep(100);
                            order.wait();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        System.out.println(dish.getName() + " был вызов метода notify: " + System.currentTimeMillis());
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(dish.getName() + "The process was interrupted");
                }
//                dish.setStatusDish(Status.READY);
                System.out.println(dish.getName() + " is ready!");
//                synchronized (this) {
//                    this.notifyAll();
//                }
            });
        }));

       System.out.println("официант ждет заказ: " + order.getId() + " with thread: " + Thread.currentThread().getName());

       do {
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
           synchronized (order) {
//               order.getDishes().forEach(dish -> System.out.println(dish));
               if (order.getDishes().stream().allMatch(dish -> dish.getStatusDish().equals(Status.READY))) {
                   order.notifyAll();
                   break;
               }
           }
       }while (true);
       System.out.println("ЗАКАЗ: " + order.getId() + " ВЫПОЛНЕН!  with thread: " + Thread.currentThread().getName());


//        chefThreadPool.execute(() -> {
//            String name = Thread.currentThread().getName();
//            System.out.println("официант ждет заказ: " + order.getId() + " with thread: " + name);
//
//                    do {
//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                        synchronized (order) {
//                            if (order.getDishes().stream().allMatch(dish -> dish.getStatusDish().equals(Status.READY))) {
//                                order.notifyAll();
//                                break;
//                            }
//                        }
//                    }while (true);
//                    System.out.println("ЗАКАЗ: " + order.getId() + " ВЫПОЛНЕН!  with thread: " + name);
//
//        });
    }

    public void shutdown() {
        chefThreadPool.shutdownNow();
    }
}
