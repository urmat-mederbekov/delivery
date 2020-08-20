package kg.attractor.demo.model;

public enum  State {
    COMPLETED {
        @Override
        public State changeState(){
            return NOT_COMPLETED;
        }
    }
    , NOT_COMPLETED {
        @Override
        public State changeState(){
            return COMPLETED;
        }
    };

    public abstract  State changeState();
}

