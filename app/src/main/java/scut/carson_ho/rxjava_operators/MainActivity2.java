package scut.carson_ho.rxjava_operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import scut.carson_ho.Crime;

/**
 * Created by JayHan on 2018/5/29.
 */

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "RxJava_2.x_实战";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        method_do_2_1();

//        method_do_2_2();//Hot Observable 如何转变成 Cold Observable

//        method_do_2_4$1();
//        method_do_2_4$2();
//        method_do_2_4$3();

//        method_2_5$1();//AsyncSubject
//        method_2_5$2();//BehaviorSubject
//        method_2_5$3();
//        method_2_5$5();

//        method_3_2$1();
//        method_3_2$2();
//        method_3_3$1();
//        method_3_3$2();

//        method_5_2$groupBy();
//        method_5_5$take();
//        method_5_8$distinct();

//        method_5_2$defaultIfEmpty();
//        method_6_3$sequenceEqual();
//        method_6_4$skipUtil();
//        method_6_4$skipWhile();
//        method_6_5$takeUtil();
//        method_6_5$takeWhile();



    }

    private void method_6_5$takeWhile() {
        Observable.just(1, 2, 3, 4, 5).takeWhile(new Predicate<Integer>() {
            @Override
            public boolean test(@NonNull Integer integer) throws Exception {
                return integer <= 2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.i(TAG, "method_6_5$takeUtil:" + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                Log.i(TAG, "method_6_5$takeUtil:" + "error");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "method_6_5$takeUtil:" + "onComplete");
            }
        });
    }

    private void method_6_5$takeUtil() {
        Observable.just(1, 2, 3, 4, 5)
                .takeUntil(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer == 5;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.i(TAG, "method_6_5$takeUtil:" + integer);
            }
        });
    }

    private void method_6_4$skipWhile() {
        Observable.just(1, 2, 3, 4, 5).skipWhile(new Predicate<Integer>() {
            @Override
            public boolean test(@NonNull Integer integer) throws Exception {
                return integer <= 2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.i(TAG, "method_6_4$skipWhile:" + integer);
            }
        });
    }

    private void method_6_4$skipUtil() {
        Observable.intervalRange(1, 9, 0, 1, TimeUnit.SECONDS)
                .skipUntil(Observable.timer(4, TimeUnit.SECONDS))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.i(TAG, "method_6_4$skipUtil:" + aLong);
                    }
                });
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void method_6_3$sequenceEqual() {
        Observable.sequenceEqual(Observable.just(1, 2, 3, 4, 5), Observable.just(1, 2, 3, 4, 5), new BiPredicate<Integer, Integer>() {
            @Override
            public boolean test(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                Log.i(TAG, "integer:" + integer);
                Log.i(TAG, "integer2:" + integer2);
                return integer == integer2;
            }
        })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        Log.i(TAG, "method_6_3$sequenceEqual:" + aBoolean);
                    }
                });
    }

    private void method_5_2$defaultIfEmpty() {
        Observable.empty().defaultIfEmpty(8).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                Log.i(TAG, "method_5_2$defaultIfEmpty:" + o);
            }
        });


    }

    private void method_5_8$distinct() {
        Observable.just(1, 2, 1, 2, 3, 4, 5, 5, 6)
//                .distinct()
                .distinct(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return null;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });

    }

    private void method_5_5$take() {
        Observable.intervalRange(0, 10, 1, 1, TimeUnit.SECONDS).takeLast(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.i(TAG, "method_5_5$take---" + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.i(TAG, "method_5_5$take---" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "complete");
                    }
                });
//        Observable.intervalRange(0, 10, 1, 1, TimeUnit.SECONDS).take(3, TimeUnit.SECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(@NonNull Long aLong) throws Exception {
//                        Log.i(TAG, "method_5_5$take---" + aLong);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        Log.i(TAG, "method_5_5$take---" + throwable.getMessage());
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        Log.i(TAG, "complete");
//                    }
//                });
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.getMessage();
        }


    }

    private void method_5_2$groupBy() {
        Observable.range(1, 9).groupBy(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return (integer % 2 == 0) ? "偶数组" : "奇数组";
            }
        }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
            @Override
            public void accept(@NonNull GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
//                Log.i(TAG, "method_5_2$groupBy---" + stringIntegerGroupedObservable.getKey());

                if (stringIntegerGroupedObservable.getKey().equals("奇数组")) {
                    stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(@NonNull Integer integer) throws Exception {
                            Log.i(TAG, stringIntegerGroupedObservable.getKey() + "member" + integer);
                        }
                    });
                }
            }
        });

        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void method_3_3$2() {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.i(TAG, "method_3_3$2---" + aLong);
                    }
                });
        Log.i(TAG, "method_3_3$2---" + "开始");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "method_3_3$2---" + "结束");
    }

    private void method_3_3$1() {
        Observable defer = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just("hello defer");
            }
        });
        defer.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String o) throws Exception {
                Log.i(TAG, "method_3_2$2---" + o);
            }
        });

    }

    private void method_3_2$2() {
        long startTimeMillis = System.currentTimeMillis();
        Observable.interval(500, TimeUnit.MILLISECONDS).take(5)
                .repeatUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        return System.currentTimeMillis() - startTimeMillis > 5000;
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                Log.i(TAG, "method_3_2$2---" + aLong);
            }
        });
        Log.i(TAG, "-------------------method_3_2$2----start--------------");
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Log.i(TAG, "-------------------method_3_2$2----end--------------");
    }

    private void method_3_2$1() {
        Observable.range(1, 5).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                return Observable.timer(10, TimeUnit.SECONDS);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.i(TAG, "method_3_2$1---" + integer);
            }
        });
        Log.i(TAG, "------------------------method_3_2$1-----------------------------");
//        try {
//            Thread.sleep(12000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private void method_2_5$5() {
        PublishSubject<String> objectPublishSubject = PublishSubject.create();
        objectPublishSubject.subscribeOn(Schedulers.io()).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "PublishSubject---" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                Log.i(TAG, "PublishSubject---" + "throwable");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "PublishSubject---" + "run///////onComplete");
            }
        });
        objectPublishSubject.onNext("Foo");
        objectPublishSubject.onNext("Bar");
        objectPublishSubject.onComplete();
        try {
            Thread.sleep(2000);
        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    private void method_2_5$3() {
        ReplaySubject<String> replaySubject = ReplaySubject.createWithSize(2);
        replaySubject.onNext("replaySubject_01");
        replaySubject.onNext("replaySubject_1.5");
        replaySubject.onNext("replaySubject_02");
        replaySubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "ReplaySubject---" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                Log.i(TAG, "ReplaySubject---error");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "ReplaySubject---Complete");
            }
        });
        replaySubject.onNext("replaySubject_03");
        replaySubject.onNext("replaySubject_04");

    }

    private void method_2_5$2() {
        BehaviorSubject<String> subject = BehaviorSubject.create();
//        subject.bufferSize();
//        subject.onNext("BehaviorSubject_0.5");
        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "BehaviorSubject---" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                Log.i(TAG, "BehaviorSubject---error");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "BehaviorSubject---Complete");
            }
        });
        subject.onNext("BehaviorSubject_02");
        subject.onNext("BehaviorSubject_03");


    }

    private void method_2_5$1() {
        AsyncSubject<String> objectAsyncSubject = AsyncSubject.create();
        objectAsyncSubject.onNext("AsyncSubject01");
        objectAsyncSubject.onNext("AsyncSubject02");
//        objectAsyncSubject.onComplete();
        objectAsyncSubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.i(TAG, "objectAsyncSubject---" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                Log.i(TAG, "objectAsyncSubject---error");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "objectAsyncSubject---Complete");
            }
        });
        objectAsyncSubject.onNext("AsyncSubject03");
        objectAsyncSubject.onNext("AsyncSubject04");
        objectAsyncSubject.onComplete();
    }

    private void method_do_2_4$2() {

        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    e.onComplete();
                } catch (Exception a) {
                    e.onError(a);
                }

            }
        }).andThen(Observable.range(1, 5)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.i(TAG, "Completable---andThen---" + integer);
            }
        });
    }

    private void method_do_2_4$1() {
//        Single.create(new SingleOnSubscribe<String>() {
//            @Override
//            public void subscribe(SingleEmitter<String> e) throws Exception {
//
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(@NonNull String s) throws Exception {
//
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(@NonNull Throwable throwable) throws Exception {
//
//            }
//        });

        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> e) throws Exception {

            }
        }).subscribe(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(@NonNull String s, @NonNull Throwable throwable) throws Exception {

            }
        });
    }

    private void method_do_2_2() {
        Consumer<Long> subscriber1 = aa -> Log.i(TAG, "subscriber1-----" + aa);

        Consumer<Long> subscriber2 = new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                Log.i(TAG, "subscriber2-----" + aLong);
            }
        };

        ConnectableObservable<Long> connectableObservable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> e) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation()).take(Integer.MAX_VALUE).subscribe(e::onNext);

//                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation()).take(Integer.MAX_VALUE).subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(@NonNull Long aLong) throws Exception {
//                        e.onNext(aLong);
//                    }
//                });
            }
        }).observeOn(Schedulers.newThread()).publish();
        connectableObservable.connect();

        Observable<Long> observable = connectableObservable.refCount();
        Disposable disposable1 = observable.subscribe(subscriber1);
        Disposable disposable2 = observable.subscribe(subscriber2);

        try {
            Thread.sleep(20L);
        } catch (Exception e) {
            e.getMessage();
        }
        disposable1.dispose();
        disposable2.dispose();

        Log.i(TAG, "重新开始--------------------------");


        disposable1 = observable.subscribe(subscriber1);
        disposable2 = observable.subscribe(subscriber2);

        try {
            Thread.sleep(20L);
        } catch (Exception e) {
            e.getMessage();
        }

    }

    private void method_do_2_1() {
        Observable.just("hello")
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.i(TAG, "doOnNext-----" + s);
                    }
                })
                .doAfterNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.i(TAG, "doAfterNext-----" + s);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "doOnComplete-----");
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.i(TAG, "doOnSubscribe-----");
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "doAfterTerminate-----");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "doFinally-----");
                    }
                })
                .doOnEach(new Consumer<Notification<String>>() {
                    @Override
                    public void accept(@NonNull Notification<String> stringNotification) throws Exception {
                        Log.i(TAG, "doOnEach-----" + (stringNotification.isOnNext() ? "onNext" : stringNotification.isOnComplete() ? "onComplete" : "onError"));
                    }
                })
                .doOnLifecycle(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.i(TAG, "doOnLifecycle-----" + disposable.isDisposed());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "doOnLifecycle run-----");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.i(TAG, "收到消息-----" + s);
                    }
                });
    }
}
