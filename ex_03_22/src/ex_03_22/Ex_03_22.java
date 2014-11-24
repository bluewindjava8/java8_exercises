
package ex_03_22;

public class Ex_03_22 {

    public static void main(String[] args) {

    }
    
}

//下記の二つのmethodはCompletableFutureのflatMapです。
//<U> CompletableFuture<U>	thenCompose(Function<? super T,? extends CompletionStage<U>> fn)
//<U> CompletableFuture<U>	thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn)