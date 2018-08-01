public class RoundRobin {

    private int index = -1;

    private int currentWeight = 0;

    private Server[] servers;

    public RoundRobin(Server[] servers){
        this.servers = servers;
    }

    static class Server{
        String name;
        int weight;
        int currentWeight;
        public Server(String name, int weight){
            this.name = name;
            this.weight = weight;
        }
    }

    private int gcd(int a, int b){
        int c;
        while(b!=0)
        {
            c = b;
            b = a % b;
            a = c;
        }
        return a;
    }

    private int getGcd(){
        int res = servers[0].weight;
        for (int i = 1; i < servers.length; i++) {
            res = gcd(res,servers[i].weight);
        }
        return res;
    }

    private int getSum(){
        int total = 0;
        for (int i = 0; i < servers.length; i++) {
            total += servers[i].weight;
        }
        return total;
    }

    private int maxWeight(){
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < servers.length; i++) {
            if (servers[i].weight>res){
                res = servers[i].weight;
            }
        }
        return res;
    }


    //LVS
    public int roundRobin(){
        while (true){
            index = (index+1)%servers.length;
            if (index==0){
                currentWeight = currentWeight-getGcd();
                if (currentWeight<=0){
                    currentWeight = maxWeight();
                    if (currentWeight==0){
                        return -1;
                    }
                }
            }
            if (servers[index].weight>=currentWeight){
                return index;
            }
        }
    }

    // soft weighted round robin algorithm
    public int softRoundRobin(){
        int total = 0;
        for (int i = 0; i < servers.length; i++) {
            servers[i].currentWeight+=servers[i].weight;
            total += servers[i].weight;
            if (index==-1 || servers[index].currentWeight<servers[index].weight){
                index  = i;
            }
        }
        servers[index].currentWeight-=total;
        return index;
    }

    public int lengthOfLongestSubstring(String s) {
        int count[] = new int[26];
        for(int i=0;i<26;i++){
            count[i]=-1;
        }
        int len=0,maxLen=Integer.MIN_VALUE;
        for(int i=0;i<s.length();i++){
            if(count[s.charAt(i)-'a']>=0){
                maxLen = Math.max(len, maxLen);
                len = 0;
                i = count[s.charAt(i)-'a'];
                for(int j=0;j<26;j++){
                    count[j]=-1;
                }
            }else {
                count[s.charAt(i) - 'a'] = i;
                len++;
            }
        }
        return Math.max(len, maxLen);
    }

    public static void main(String[] args) {
        Server servers[] = new Server[]{new Server("a",5),new Server("b",1),new Server("c",1)};
        RoundRobin roundRobin = new RoundRobin(servers);
        for (int i = 0; i < roundRobin.getSum(); i++) {
            int index = roundRobin.softRoundRobin();
            System.out.printf(servers[index].name+"("+servers[index].weight+")");
        }

        System.out.println(roundRobin.lengthOfLongestSubstring("qpxrjxkltzvx"));
    }



}
