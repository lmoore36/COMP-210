package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;

    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    private int balanceFactor() {
        if (isEmpty())
            return 0;

        int left = _left != null ? _left.height() : -1;
        int right = _right != null ? _right.height() : -1;

        return left - right;
    }

    private AVLTree<T> rebalance() {
        _height = Math.max(_left.height(), _right.height())+1;
        int balance = balanceFactor();
        if(balance < -1) {
            if (_right.balanceFactor() > 0) {
                _right = this._right.rotateRight();
            }
            return this.rotateLeft();
        }

        if (balance > 1) {
            if (_left.balanceFactor() < 0) {
                _left = _left.rotateLeft();
            }
            return this.rotateRight();
        }
        return this;
    }

    private AVLTree<T> rotateLeft() {
        AVLTree<T> newParent = _right;
        _right = newParent._left;
        newParent._left = this;

        _height = 1 + Math.max(_left.height(), _right.height());
        _size = _left._size + _right._size + 1;

        newParent._height = Math.max(newParent._left.height(),
                newParent._right.height())+1;
        newParent._size = newParent._left._size
                + newParent._right._size + 1;

        return newParent;
    }

    private AVLTree<T> rotateRight() {
        AVLTree<T> newParent = _left;
        _left = newParent._right;
        newParent._right = this;

        _height = Math.max(_left.height(), _right.height())+1;
        _size = _left._size + _right._size + 1;

        newParent._height = Math.max(newParent._left.height(), newParent._right.height())+1;
        newParent._size = newParent._left._size + newParent._right._size + 1;

        return newParent;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
        if (isEmpty()) {
            _value = element;
            _left = new AVLTree<T>();
            _right = new AVLTree<T>();
            _size++;
            _height = Math.max(_left.height(), _right.height()) + 1;
        }

        else if(element.compareTo(getValue()) >= 0) {
            _right = (AVLTree<T>) _right.insert(element);
            _height = Math.max(_left.height(), _right.height()) + 1;
            _size++;
        }
        else{
            _left = (AVLTree<T>) _left.insert(element);
            _height = Math.max(_left.height(), _right.height()) + 1;
            _size++;
        }

        return rebalance();
    }


    @Override
    public SelfBalancingBST<T> remove(T element) {
        if(_value == null){
            return this;
        }

        if (element.compareTo(_value) == 0) {
            if (_left.isEmpty() && _right.isEmpty()) {
                return new AVLTree<T>();
            } else if (_left.isEmpty()) {
                return _right;
            } else if (_right.isEmpty()) {
                return _left;
            } else {
                T successor = _right.findMin();
                _right = (AVLTree<T>) _right.remove(successor);
                _value = successor;
            }
        }
        else if(element.compareTo(_value) < 0){
            _left = (AVLTree<T>) _left.remove(element);
        }
        else if(element.compareTo(_value) > 0) {
            _right = (AVLTree<T>) _right.remove(element);
        }

        _height = Math.max(_left.height(), _right.height()) + 1;
        _size = _left._size + _right._size + 1;

        return rebalance();
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_left.isEmpty()) {
            return _value;
        } else {
            return _left.findMin();
        }
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_right.isEmpty()) {
            return _value;
        } else {
            return _right.findMax();
        }
    }

    @Override
    public boolean contains(T element) {
        if (isEmpty()) {
            return false;
        }

        if (_value.compareTo(element) > 0) {
            return _left.contains(element);
        }

        else if (_value.compareTo(element) < 0) {
            return _right.contains(element);
        }

        else {
            return true;
        }
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
        return _right;
    }

    public void printTree() {
        printTree("", this, false);
    }

    private void printTree(String prefix, AVLTree<T> node, boolean isRight) {
        if (node != null) {
            System.out.println(prefix + (isRight ? "├── " : "└── ") + node._value);
            printTree(prefix + (isRight ? "│   " : "    "), node._right, true);
            printTree(prefix + (isRight ? "│   " : "    "), node._left, false);
        }
    }
}