# RxJava explained in a simple way

I had taken a session on RxJava some months back. These are the notes I had prepared for it. Read this if you are a starter in Reactive Programming or RxJava. 
<br/><br/>

For a moment, forget RxJava.
Just answer some questions. <br/>


1. If your API returns you phone numbers of a user from server but for every phone number returned, you have to validate if its an Indian phone number or not and want to show just Indian phone numbers on the UI. How would you do it ?


2. If you want to read names of 1000 users from Sqlite DB and also need to read fetch profile images of the same 1000 users from server. Then you need to map every user name with the correct profile image and show it on the UI. How would you do it ?


3. You have made a chat App. User A sends a message to User B. There is no internet in User A’s phone. You want to write a retry policy for sending the message. If no internet, try again after 2 seconds - > if still no internet, try again after 5 seconds -> still no internet, try again after 10 seconds … and the time increases on every try. How would you do it ?


4. Let’s suppose you have 5 classes doing different things and in total you have 15 callbacks. How can you make the code more readable and maintainable ?


5. You get two list of same size, list 1 and list 2. See this situation. Fire API 1 -> Get list 1, Fire API 2 - > Get list 2.  For every item in list 1 or list 2,  Fire API 3 with parameters consisting of data from list 1 and data from list 2. How can you achieve that ?


6. Let’s suppose you have a home screen (MainActivity). On the MainActivity, you have 3 RecyclerView (RecyclerView1, RecyclerView2, RecyclerView3) placed in a vertical order one after the other in a LinearLayout. 

   RecyclerView1, gets updated after getting the data from SQLite database.
   RecyclerView2 and RecyclerView3 get updated after getting data from an API call.

   In Synchronous programming, how would you do it ? If you have a plan to execute it in the traditional way line by line,     there is one problem that you might face. That is, RecyclerView2 will not be updated until the updation of RecyclerView1 is  over. If RecyclerView1 is taking a lot of time to get updated, you see no data for RecyclerView2 and RecyclerView3.


   Will it look good ?



For all these above tasks there is a better structure which if adopted will solve many problems.

<br>

<strong>What is RxJava ??</strong>

Some people created something called as Reactive Programming or simply say ReactiveX. This is the website of ReactiveX - http://reactivex.io/ .. 

As per its definition, ReactiveX is a library for making asynchronous and event-based programs by using observable sequences. 
That means, you divide tasks into blocks consisting of Observable and Observer. Every block does a particular task in an asynchronous was and is not dependant on any other block.

RxJava is just the Java implementation of Reactive programming.

In point 6 above, for the case of updating the 3 RecyclerViews, we make 3 separate blocks for getting the data and updating the respective RecyclerViews. 

RecyclerView 1 is updated when it has finished getting the data from SQLite database. The API calls for getting data to update RecyclerView 2 and 3, won’t have to wait for updation of RecyclerView 1. 

Those 2 API calls will happen separately and RecyclerView 2 and 3 will be updated regardless of whether the SQLite operation for getting data to update RecyclerView 1 is over or not. 

<br>

As I have told you do RxJava operations in separate blocks. Every RxJava block is composed of two key components: Observable and Observer. In addition to these, there are other things like Schedulers, Operators and Subscription.

   1. (OBSERVABLE) is the part of the block that does the work and gives data.
     
   2. (OBSERVER) keeps looking the OBSERVABLE and fetches the data from it. 
    
   3. (SUBSCRIPTION) joins the Observable with the Observer. There can be multiple Observers subscribed to a single Observable.
     
   4. (SCHEDULER) is just a parameter that is to be supplied in the Subscription which basically tells on which thread the Observable and the Observer should run. Normally, the Observer has to update the data on the UI which it gets from the Observable, so it should run on the main thread and Observable does heavy work, should run on any background thread. 
    
   5. (OPERATOR) just modifies the data it gets from Observable before giving it to Observer. A big power of RxJava comes in operators. You can modify the data in many ways before giving it to the observer. Like in point 1 above, for every phone number returned by Observable, you validate it using an operator and only give those data to the Observer which pass the validation. 

<br>
    
<strong>Real life code scenario</strong>

For an SQLite operation, Observable is the part that queries the DB and fetches the data. It is a heavy work. It runs on a background thread.

Observer is the part that gets the data from the Observable and updates the UI. The Observer here ha to handle UI changes, so it runs in the main UI thread.

Observable after doing any work will not give the data to anyone until a Observer attaches to it. So, Subscription here connects the Observable and the Observer. There can be multiple Observers subscribed to a single Observable.

<b>Keep in mind that, you have to Unsubscribe the Observer after the work is over, otherwise you might get memory leaks. 
</b>
<br/><br/>


Enough has been said in theory. <b>Let’s see code.</b> <em>Just import the project in this repository on Android studio. Every Activity has got lots of comments which are self explanatory. Just go through the source code, you will understand. </em>
