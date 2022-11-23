package com.example.crosshelper.editor;

import java.util.Stack;

public class ActionsStack {
    private final Stack<ImageAction> stack;
    private final int fixedSize;
    public int currentIndex = -1;

    public ActionsStack(int fixedSize) {
        stack = new Stack<>();
        this.fixedSize = fixedSize;
    }

    public ImageAction peekWithPosition() {
        if (currentIndex >= stack.size() || currentIndex < 0)
            return null;
        else
            return stack.get(currentIndex--);
    }

    public ImageAction reversePeekWithPosition() {
        if (currentIndex + 1 >= stack.size() || currentIndex + 1 < 0) {
            return null;
        } else {
            currentIndex++;
            return stack.get(currentIndex);
        }
    }

    public boolean isStartPosition() {
        return (currentIndex == (stack.size() - 1));
    }

    public void push(ImageAction imageAction) {
        if (isActionInStack(imageAction))
            return;

        if (stack.size() >= fixedSize) {
            stack.removeElementAt(0);
        } else
            currentIndex++;
        stack.push(imageAction);
    }

    public void clear() {
        currentIndex = -1;
        stack.clear();
    }

    @SuppressWarnings("unchecked")
    private boolean isActionInStack(ImageAction imageAction) {
        Stack<ImageAction> copy = (Stack<ImageAction>) stack.clone();

        for (int i = 0; i < stack.size(); i++) {
            ImageAction currentImageAction = copy.pop();
            if (
                    currentImageAction.x == imageAction.x &&
                            currentImageAction.y == imageAction.y &&
                            currentImageAction.isActivated == imageAction.isActivated
            )
                return true;
        }
        return false;
    }
}
