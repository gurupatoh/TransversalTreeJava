/**Recursive function to insert a new Data item at the correct leaf*/
protected void insert(Data item) {
    int diff = dataOne.compare(item);

    //Node is a leaf
    if ((left == null) && (mid == null) && (right == null)) {
        if (this.dataTwo == null) {   //Leaf has only one data item
            if (diff <= 0) {    //New item is larger than dataOne
                this.dataTwo = item;
            } else {            //New item is smaller than dataOne
                this.dataTwo = dataOne;
                this.dataOne = item;
            }
        } else {                //Leaf has two items and must be split
            splitLeaf(item);   

            if (parent != null) {   
                this.parent.pushUp(this);
            }
        }

        return;
    }

    //Not a leaf, continue traversal
    if (diff > 0) {       //New item is smaller than dataOne and the smallest
        left.insert(item);
    }                     //New item is larger or equal to dataOne and this node
    else if (dataTwo == null) {      //is a 2-node
        right.insert(item);
    } else {              //This node is a 3-node
        diff = dataTwo.compare(item);

        if (diff > 0) {   //New item is smaller than dataTwo
            mid.insert(item);
        } else {          //New item is larger than dataTwo and the largest
            right.insert(item);
        }
    }
}