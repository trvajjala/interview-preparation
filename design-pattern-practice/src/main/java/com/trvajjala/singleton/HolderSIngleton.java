package com.trvajjala.singleton;

public class HolderSIngleton {

    static class Holder {
        public static HolderSIngleton INSTANCE = new HolderSIngleton();
    }

    public static HolderSIngleton getInstance() {
        return Holder.INSTANCE;
    }

}
